package com.hut.demo.spark;

/**
 * Created by crazyacking on 2017/1/1.
 */

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

public class DemoReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
    public DemoReducer() {
    }

    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        int sum;
        for (sum = 0; values.hasNext(); sum += values.next().get()) {
        }

        output.collect(key, new IntWritable(sum));
    }
}