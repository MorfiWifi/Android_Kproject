package com.apps.morfiwifi.morfi_project_samane.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.Ticket;
import com.apps.morfiwifi.morfi_project_samane.ui.site_master.SiteTicketActivity;
import com.apps.morfiwifi.morfi_project_samane.ui.site_master.SiteTicketStatsActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;
import com.parse.ParseQuery;
import com.wang.avi.AVLoadingIndicatorView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolder_statestic extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView title;
    TextView count;
    AVLoadingIndicatorView loading;
    RelativeLayout back;
//    Ticket ticket;
    SiteTicketStatsActivity.query_id queryType ;
    AppCompatActivity activity;

    public ViewHolder_statestic(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.tv_title);
        count   = itemView.findViewById(R.id.tv_count);
        loading   = itemView.findViewById(R.id.avi);
        back   = itemView.findViewById(R.id.rel_back);
    }

    @Override
    public void onClick(View v) {
        // TODO: 11/18/2018 start ACtivity ! I guess! => have fun , some
        Intent intent =new Intent(activity , SiteTicketActivity.class);
        switch (queryType){
            case erja_to_site:
                intent.putExtra(SiteTicketActivity.Query_Part , SiteTicketActivity.ERJA_BASE);
                break;
            case outternals:
                intent.putExtra(SiteTicketActivity.Query_Part , SiteTicketActivity.NOTINTERNAL);
                break;
            case internals:
                intent.putExtra(SiteTicketActivity.Query_Part , SiteTicketActivity.INTERNAL);
                break;
            case students:
                intent.putExtra(SiteTicketActivity.Query_Part , SiteTicketActivity.STUDENT);
                break;
            case closes:
                intent.putExtra(SiteTicketActivity.Query_Part , SiteTicketActivity.CLOSED);
                break;
            case opens:
                intent.putExtra(SiteTicketActivity.Query_Part , SiteTicketActivity.OPEN);
                break;
            case all:
                intent.putExtra(SiteTicketActivity.Query_Part , SiteTicketActivity.ALL);
                break;
            default:

        }
        if (!intent.hasExtra(SiteTicketActivity.Query_Part)) return;
        intent.putExtra(SiteTicketActivity.USER_MOD , SiteTicketActivity.ADMINIMISTRATOR);
        activity.startActivity(intent);
    }
}
