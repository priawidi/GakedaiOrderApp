package com.mobcom.gakedaiorderapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.api.ApiClient;
import com.mobcom.gakedaiorderapp.model.cart.PostCartModel;
import com.mobcom.gakedaiorderapp.model.menu.MenuModel;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteAddAdapter extends RecyclerView.Adapter<FavoriteAddAdapter.ViewHolder> {

    private static final String TAG = "FavoriteAddAdapter";
    List<MenuModel> mlistMenu;

    public FavoriteAddAdapter(List<MenuModel> listMenu) {
        this.mlistMenu = listMenu;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_favorite_add_item, parent, false);
        FavoriteAddAdapter.ViewHolder viewHolder = new FavoriteAddAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = mlistMenu.get(position).getName();
        String price = mlistMenu.get(position).getPrice();
        String detail = mlistMenu.get(position).getDetail();
        String photo = mlistMenu.get(position).getPhoto();
        holder.tv_item_name.setText(name);
        holder.tv_item_price.setText("Rp." + price);
        holder.tv_item_detail.setText(detail);
        Picasso.get().load("https://admin.gakedai.com/api/menu/" + photo).into(holder.iv_item_photo);

        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(v.getContext());
                String pName = acct.getDisplayName();
                String pId = acct.getId();

                ApiClient.endpoint().sendCart(pId, pName, name, price, photo).enqueue(new Callback<PostCartModel>() {
                    @Override
                    public void onResponse(Call<PostCartModel> call, Response<PostCartModel> response) {

                        Log.d(TAG, "onResponse: Favorite Added ");

                    }

                    @Override
                    public void onFailure(Call<PostCartModel> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlistMenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item_name, tv_item_price, tv_item_detail;
        ImageView iv_item_photo;
        ImageButton btn_add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item_name = itemView.findViewById(R.id.tv_favorite_add_item_name);
            tv_item_price = itemView.findViewById(R.id.tv_favorite_add_item_price);
            tv_item_detail = itemView.findViewById(R.id.tv_favorite_add_item_detail);
            iv_item_photo = itemView.findViewById(R.id.iv_favorite_add_item_photo);
            btn_add = itemView.findViewById(R.id.btn_add_fav_user);

        }
    }

}
