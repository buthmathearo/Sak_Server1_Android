package com.buthmathearo.sak_server1.adapter;

import com.buthmathearo.sak_server1.R;
import com.buthmathearo.sak_server1.databinding.SingleRowUserBinding;
import com.buthmathearo.sak_server1.listener.UserListener;
import com.buthmathearo.sak_server1.model.User;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by buthmathearo on 6/12/16.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> mList;
    private Context mContext;
    private UserListener mUserListener;


    public UserAdapter(List<User> pList, UserListener userListener){
        this.mList = pList;
        this.mUserListener = userListener;
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        private SingleRowUserBinding mBinding;
        public UserViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mUserListener.onClick(mList.get(getAdapterPosition()));
                }
            });
        }
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_row_user, parent, false);
        mContext = view.getContext();
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        Picasso.with(mContext)
                .load(mList.get(position).getAvatar_url())
                .into(holder.mBinding.imageView);
        holder.mBinding.login.setText(mList.get(position).getLogin());
        holder.mBinding.type.setText(mList.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
