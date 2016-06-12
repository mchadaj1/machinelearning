package com.example.algorithmExecuting;

import com.example.hunterPreyPredator.ExperimentRunner;
import com.example.entities.*;
import com.example.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Klasa obsługująca moduł Cron Task
 */
@Service("algorithmExecutor")
public class AlgorithmsCronExecutor {

    AlgorithmExecutionRepository algorithmExecutionRepository;
    ProblemConfigurationRepository problemConfigurationRepository;
    ProblemParamValueRepository problemParamValueRepository;
    MethodParamValueRepository methodParamValueRepository;
    StatisticRepository statisticRepository;

    MethodConfigurationRepository methodConfigurationRepository;
    @Autowired
    public void setAlgorithmExecutionRepository(AlgorithmExecutionRepository algorithmExecutionRepository) {
        this.algorithmExecutionRepository = algorithmExecutionRepository;
    }
    @Autowired
    public void setProblemConfigurationRepository(ProblemConfigurationRepository problemConfigurationRepository) {
        this.problemConfigurationRepository = problemConfigurationRepository;
    }
    @Autowired
    public void setProblemParamValueRepository(ProblemParamValueRepository problemParamValueRepository) {
        this.problemParamValueRepository = problemParamValueRepository;
    }
    @Autowired
    public void setMethodParamValueRepository(MethodParamValueRepository methodParamValueRepository) {
        this.methodParamValueRepository = methodParamValueRepository;
    }
    @Autowired
    public void setStatisticRepository(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }
    @Autowired
    public void setMethodConfigurationRepository(MethodConfigurationRepository methodConfigurationRepository) {
        this.methodConfigurationRepository = methodConfigurationRepository;
    }

    /**
     * Funkcja wybierająca symulacje do wykonania z bazy danych
     */
    protected void lookForPendingTask() {

        List<AlgorithmExecution> algorithmExecutions = algorithmExecutionRepository.findByPendingTrueAndCompletedFalse();


        if(!algorithmExecutions.isEmpty()) {
            String filename = new BigInteger(256, new Random()).toString(32).substring(2,20)+".txt";
            OutputStream outputStream;
            try {
                Path newPath = Files.createFile(Paths.get("executionslogs/" + filename));
                outputStream = Files.newOutputStream(newPath);

            outputStream.write(new String("Wybrano polecenie wykonania algorytmu o id " + algorithmExecutions.get(0).getId().toString() + "\n").getBytes());
            Map<String,String> attributes = new HashMap<String, String>();
            Map<String, String> methodAttributes = new HashMap<>();


            ProblemConfiguration problemConfiguration = problemConfigurationRepository.findById(algorithmExecutions.get(0).problemConfiguration.getId());
            List<ProblemParamValue> problemParamValues = problemParamValueRepository.findByProblemConfigurationId(problemConfiguration.getId());
            List<MethodParamValue> methodParamValues = methodParamValueRepository.findByMethodConfigurationId(algorithmExecutions.get(0).getMethodConfigurationId());
                outputStream.write(new String("Rozpoczęto ładowanie argumentów: \n").getBytes());
                outputStream.write(new String("Argumenty metody:\n").getBytes());
            for (MethodParamValue methodParamValue : methodParamValues) {
                outputStream.write(new String(methodParamValue.method_param.getName() +" "+methodParamValue.getValue()).getBytes());
                methodAttributes.put(methodParamValue.method_param.getName(), methodParamValue.getValue());
            }
                outputStream.write(new String("Argumenty problemu:\n").getBytes());
            for(ProblemParamValue problemParamValue : problemParamValues) {
                outputStream.write(new String(problemParamValue.problem_param.getName()+" "+problemParamValue.getValue() + "\n").getBytes());
                attributes.put(problemParamValue.problem_param.getName(),problemParamValue.getValue());
            }

            algorithmExecutions.get(0).setPending(false);
            algorithmExecutions.get(0).setFilename(filename);
            algorithmExecutionRepository.save(algorithmExecutions.get(0));

            MethodConfiguration methodConfiguration = methodConfigurationRepository.findById(algorithmExecutions.get(0).getMethodConfigurationId());
            Method method = methodConfiguration.getMethod();
                Map<String, String> replaces = new HashMap<>();
                replaces.put("#Globals",method.getGlobals());
                replaces.put("#nextStepMethod",method.getCode());
                replaces.put("#Imports",method.getImports());
                replaces.put("#Constructor",method.getConstructorcode());
                replaces.put("#finishGame",method.getFinishgame());
            Class<?> hunterClass = InlineCompiler.getAgent(replaces, outputStream);
            if(hunterClass == null) {
                outputStream.write(new String("Nie udało się utworzyć klasy łowcy\n").getBytes());
                outputStream.close();
                algorithmExecutions.get(0).setCompleted(true);
                algorithmExecutionRepository.save(algorithmExecutions.get(0));
                return;
            }
                int experiments = Integer.parseInt(attributes.get("experiments"));

            for(int i = 1; i <= experiments; i++) {
                ExperimentRunner experimentRunner = new ExperimentRunner(attributes, methodAttributes, hunterClass, outputStream);
                try {
                    experimentRunner.init();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }catch(NumberFormatException ex) {
                    outputStream.write(new String("Wystąpił problem z wartością parametru\n").getBytes());
                }
                experimentRunner.run();
                outputStream.write(new String("Zakończono wykonywanie eksperymentu nr " + i+"\n").getBytes());
                List<Statistic> statistics = experimentRunner.getStatistics();
                final int a = i;
                statistics.stream()
                        .forEach(b -> b.setExperiment_number(a));
                statistics.stream()
                        .forEach(b -> b.setExecutionId(algorithmExecutions.get(0).getId()));

                statisticRepository.save(statistics);
            }

            algorithmExecutions.get(0).setCompleted(true);
            algorithmExecutionRepository.save(algorithmExecutions.get(0));
            outputStream.write(new String("Zakończono wykonywanie algorytmu\n").getBytes());
            outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
                algorithmExecutions.get(0).setCompleted(true);
                algorithmExecutionRepository.save(algorithmExecutions.get(0));
                return;
            }
            catch(NumberFormatException ex) {

            }


        }

        else
            System.out.println("Pusto, nie ma zadań do wykonania");

    }
}
