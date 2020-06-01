package com.boiz.covidcasecounter;

import com.boiz.covidcasecounter.Data;

import java.util.List;

interface DataAsyncResponse {
    void processFinished(List<Data> dataArrayList);
}
