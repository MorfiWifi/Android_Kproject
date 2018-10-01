package com.apps.morfiwifi.morfi_project_samane.util;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

public class MyJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        // runs on the main thread, so this Toast will appear
//        Toast.makeText(this, "JOBSERVICES", Toast.LENGTH_SHORT).show();
        // perform work here, i.e. network calls asynchronously

        // returning false means the work has been done, return true if the job is being run asynchronously
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        // if the job is prematurely cancelled, do cleanup work here

        // return true to restart the job
        return false;
    }
}
