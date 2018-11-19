package com.apps.morfiwifi.morfi_project_samane.models;

import com.apps.morfiwifi.morfi_project_samane.ui.site_master.SiteTicketStatsActivity;

public class Stats {
    public String title;
    public String value;
    public boolean isLoading;
    public SiteTicketStatsActivity.query_id queryType;

    public Stats(String title , String value , boolean isLoading){
        this.title = title;
        this.value = value;
        this.isLoading = isLoading;
    }

    public Stats(String title , String value , boolean isLoading ,SiteTicketStatsActivity.query_id id ){
        this.title = title;
        this.value = value;
        this.isLoading = isLoading;
        queryType = id;
    }
}
