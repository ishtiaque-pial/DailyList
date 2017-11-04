package pial.com.dailylist.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pial.com.dailylist.Model.AdpaterModel.ContentList;
import pial.com.dailylist.Model.AdpaterModel.Header;
import pial.com.dailylist.Model.AdpaterModel.TotalList;
import pial.com.dailylist.Model.ShoppingDetails;
import pial.com.dailylist.R;
import pial.com.dailylist.ui.ItemClickListener;

/**
 * Created by pial on 11/3/17.
 */

public class ShoppingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<TotalList> totalLists = new ArrayList<>();
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private ItemClickListener listener;

    public ShoppingAdapter(ArrayList<TotalList> totalLists, ItemClickListener listener) {
        this.totalLists = totalLists;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEADER)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_layout, parent, false);
            return  new VHHeader(v);
        }
        else
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.deatls_layout, parent, false);
            return new VHItem(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof VHHeader)
        {
            // VHHeader VHheader = (VHHeader)holder;
            Header  currentItem = (Header) totalLists.get(position);
            VHHeader VHheader = (VHHeader)holder;
            VHheader.headerTV.setText(currentItem.getDate());
        }
        else if(holder instanceof VHItem)
        {
            ContentList currentItem = (ContentList) totalLists.get(position);
            VHItem VHitem = (VHItem)holder;
            VHitem.causeTV.setText(currentItem.getCause());
            VHitem.priceTV.setText("Price: "+String.valueOf(currentItem.getPrice()));
        }
    }

    @Override
    public int getItemCount() {
        return totalLists.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return totalLists.get(position) instanceof Header;
    }

    class VHHeader extends RecyclerView.ViewHolder{
        @BindView(R.id.heading)
        TextView headerTV;
        public VHHeader(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class VHItem extends RecyclerView.ViewHolder {
        @BindView(R.id.cause)
        TextView causeTV;
        @BindView(R.id.price)
        TextView priceTV;
        private long then = 0;
        public VHItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ContentList currentItem = (ContentList) totalLists.get(getAdapterPosition());
                    listener.itemOnLongClickListener(currentItem.getPosition());
                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ContentList currentItem = (ContentList) totalLists.get(getAdapterPosition());
                    listener.itemOnClickListener(currentItem.getPosition());
                }
            });


        }
    }
}
