package com.hut.demo.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by crazyacking on 2016/12/29.
 */
public class WordsCounter {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: SparkWordsCountor <file> <file>");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputFile = args[1];

        System.out.println("Copied file from " + args[0] + " to " + args[1]);

        WordsCounter counter = new WordsCounter();
        Integer exitCode = counter.count(inputFile, outputFile);
        System.exit(exitCode);
    }

    public Integer count(String inputFile, String outputFile) {

        SparkConf sparkConf = (new SparkConf()).setAppName("SparkWordsCountor");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        try {
            JavaRDD<String> input = sc.textFile(inputFile);
            JavaRDD<String> words = input.flatMap(
                    new FlatMapFunction<String, String>() {
                        public Iterator<String> call(String s) throws Exception {
                            return Arrays.asList(s.split(" ")).iterator();
                        }
                    }
            );

            JavaPairRDD<String, Integer> counts = words.mapToPair(
                    new PairFunction<String, String, Integer>() {
                        public Tuple2<String, Integer> call(String s) throws Exception {
                            return new Tuple2<String, Integer>(s, 1);
                        }
                    }
            ).reduceByKey(new Function2<Integer, Integer, Integer>() {
                public Integer call(Integer x, Integer y) throws Exception {
                    return x + y;
                }
            });
            counts.saveAsTextFile(outputFile);
        } catch (Exception x) {
            return 1;
        } finally {
            sc.stop();
        }
        return 0;
    }
}
