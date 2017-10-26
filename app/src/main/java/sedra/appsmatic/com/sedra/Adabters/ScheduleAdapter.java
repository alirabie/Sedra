package sedra.appsmatic.com.sedra.Adabters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import sedra.appsmatic.com.sedra.API.Models.PresentCards.Cardschedule;
import sedra.appsmatic.com.sedra.API.Models.VendorDateSchedule.ResVendorsSch;
import sedra.appsmatic.com.sedra.API.Models.WishListItems.Item;
import sedra.appsmatic.com.sedra.Activites.Home;
import sedra.appsmatic.com.sedra.Activites.PresentCard;
import sedra.appsmatic.com.sedra.R;

/**
 * Created by Eng Ali on 10/18/2017.
 */
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.VH2000> {

    private Context context;
    private ResVendorsSch resVendorsSch;

    public ScheduleAdapter(Context context, ResVendorsSch resVendorsSch) {
        this.context = context;
        this.resVendorsSch = resVendorsSch;
    }

    @Override
    public VH2000 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH2000(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_delivery_time,parent,false));
    }

    @Override
    public void onBindViewHolder(final VH2000 holder, final int position) {
        animate(holder);



        DateFormatSymbols dfs = new DateFormatSymbols(Locale.getDefault());
        String weekdays[] = dfs.getWeekdays();
        holder.dayTv.setText(weekdays[resVendorsSch.getDeliveryschedules().get(position).getDay()+1]);

        //Date setup
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat DesiredFormat = new SimpleDateFormat("hh:mm a",Locale.ENGLISH);

        // 'a' for AM/PM

        Date date = null;
        try {
            date = sourceFormat.parse(resVendorsSch.getDeliveryschedules().get(position).getTimefrom());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            date = sourceFormat.parse(resVendorsSch.getDeliveryschedules().get(position).getTimeto());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String formattedDateFrom = DesiredFormat.format(date.getTime());
        String fromatedDateTo=DesiredFormat.format(date.getTime());
        holder.timeTv.setText(context.getResources().getString(R.string.from)+" "+formattedDateFrom+" "+context.getResources().getString(R.string.to)+" "+fromatedDateTo);

        //select button
        holder.selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
                holder.selectButton.clearAnimation();
                holder.selectButton.setAnimation(anim);

                DateFormatSymbols dfs = new DateFormatSymbols(Locale.US);
                String weekdays[] = dfs.getWeekdays();
                PresentCard.cardschedule2.setScheduleId(resVendorsSch.getDeliveryschedules().get(position).getId());
                PresentCard.cardschedule2.setDeliveryDay(weekdays[resVendorsSch.getDeliveryschedules().get(position).getDay()+1]);
                PresentCard.cardschedule2.setDeliveryTime(resVendorsSch.getDeliveryschedules().get(position).getTimefrom());
                PresentCard.gitSchudle=true;
                Toast.makeText(context,context.getResources().getString(R.string.savesuccsess),Toast.LENGTH_LONG).show();

            }
        });
    }




    @Override
    public int getItemCount() {
        return resVendorsSch.getDeliveryschedules().size();
    }

    //Animate Adapter
    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.fadein);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

    public static class VH2000 extends RecyclerView.ViewHolder{
        private TextView dayTv,timeTv;
        private LinearLayout selectButton;

        public VH2000(View itemView) {
            super(itemView);
            dayTv=(TextView)itemView.findViewById(R.id.day_tv);
            timeTv=(TextView)itemView.findViewById(R.id.time_tv);
            selectButton=(LinearLayout)itemView.findViewById(R.id.selecttimebutton);
        }
    }



}
