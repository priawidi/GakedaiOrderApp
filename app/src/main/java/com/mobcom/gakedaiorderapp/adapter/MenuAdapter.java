package com.mobcom.gakedaiorderapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.api.ApiClient;
import com.mobcom.gakedaiorderapp.model.MenuModel;
import com.mobcom.gakedaiorderapp.model.PostCartModel;
import com.mobcom.gakedaiorderapp.ui.cart.CartFragment;
import com.mobcom.gakedaiorderapp.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MenuModel> mlistMenu;
    private static final String TAG = "MenuAdapter";
    int count = 0;
    private Context context;

    public MenuAdapter(List<MenuModel> listMenu) {
        this.mlistMenu = listMenu;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView  tv_name, tv_price, tv_detail, tv_status, tv_btn;
        public ImageButton btn_plus, btn_min;
        public ImageView tv_photo;
        public CardView btn_send;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_photo = itemView.findViewById(R.id.tv_photo);
            tv_detail = itemView.findViewById(R.id.tv_detail);
            tv_status = itemView.findViewById(R.id.tv_status);

            tv_btn = itemView.findViewById(R.id.tv_btn_qty);
            btn_plus = itemView.findViewById(R.id.btn_plus);
            btn_min = itemView.findViewById(R.id.btn_min);
            btn_send = itemView.findViewById(R.id.btn_send_to_cart);
        }

        public TextView getTv_name() {
            return tv_name;
        }
        public TextView getTv_detail() {
            return tv_detail;
        }
        public ImageView getTv_photo() {
            return tv_photo;
        }
        public TextView getTv_price() {
            return tv_price;
        }
        public TextView getTv_status() {
            return tv_status;
        }
        public TextView getTv_btn() {
            return tv_btn;
        }
        public ImageButton getBtn_plus() {
            return btn_plus;
        }
        public ImageButton getBtn_min() {
            return btn_min;
        }
        public CardView getBtn_send() {
            return btn_send;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_menu_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        if(count>0){
            v.findViewById(R.id.add_menu_layout).setVisibility(View.VISIBLE);
        }
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        String Name = mlistMenu.get(position).getName();
        //String Qty = mlistMenu.get(position).getPrice();
        String Price = mlistMenu.get(position).getPrice();
        String Photo = mlistMenu.get(position).getPhoto();
        String Detail = mlistMenu.get(position).getDetail();
        String Status = mlistMenu.get(position).getStatus();
        viewHolder.getTv_name().setText("nama " + Name);
        viewHolder.getTv_price().setText("harga " + Price);
        viewHolder.getTv_detail().setText("detail " + mlistMenu.get(position).getDetail());
        viewHolder.getTv_status().setText("status " + mlistMenu.get(position).getStatus());
//        Log.d(TAG, "onBindViewHolder: " + mlistMenu.get(position).getPhoto());
        Picasso.get().load("https://admin.gakedai.com/api/menu/" + mlistMenu.get(position).getPhoto()).into(viewHolder.getTv_photo());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent mIntent = new Intent(v.getContext(), HomeFragment.class);
                if(count<0){
                    //Toast.makeText(v.getContext(), "Gabisa mines bos", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d(TAG, "onClick: Visible?");
                    v.findViewById(R.id.add_menu_layout).setVisibility(View.VISIBLE);
                    v.findViewById(R.id.btn_send_to_cart).setVisibility(View.VISIBLE);
                    viewHolder.getTv_btn().setText(String.valueOf(count));
                    //BTN PLUS
                    viewHolder.getBtn_plus().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "onClick: Clicked?" + count);
                            count ++;
                            viewHolder.getTv_btn().setText(String.valueOf(count));

                        }
                    });
                    //BTN MINUS
                    viewHolder.getBtn_min().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "onClick: Clicked?" +count);
                            if(count<=0){
                                //Toast.makeText(v.getContext(), "Gabisa mines bos", Toast.LENGTH_SHORT).show();
                            }else{
                                count --;
                                viewHolder.getTv_btn().setText(String.valueOf(count));
                                mIntent.putExtra("count",count);
                            }
                            viewHolder.getTv_btn().setText(String.valueOf(count));

                        }
                    });
                }
            }
        });

        //BTNSEND
        viewHolder.getBtn_send().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count!=0){
                    String Qty = Integer.toString(count);
                    sendToCart(Name, Qty, Price, Photo);
                    Log.d(TAG, "onClick: SENT" + Name + Qty + Price + Photo);
                    count = 0;
                    viewHolder.getTv_btn().setText(String.valueOf(count));
                    //v.findViewById(R.id.add_menu_layout).setVisibility(View.GONE);
                    v.findViewById(R.id.btn_send_to_cart).setVisibility(View.GONE);
//                    v.findViewById(R.id.layout_menu).setVisibility(View.GONE);
                }



            }
        });
    }

    @Override
    public int getItemCount() {
        return mlistMenu.size();
    }

    private void sendToCart(String itemQty, String itemName, String itemPrice, String itemPhoto){
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(context);
        String userId = acct.getId();
        String userName = acct.getDisplayName();
        ApiClient.endpoint().sendCart(userId, userName,  itemName, itemQty, itemPrice, itemPhoto).enqueue(new Callback<PostCartModel>() {
            @Override
            public void onResponse(Call<PostCartModel> call, Response<PostCartModel> response) {
                Log.d(TAG, "onResponse: Bisa Bos" +response.code());
            }

            @Override
            public void onFailure(Call<PostCartModel> call, Throwable t) {
                Log.d(TAG, "onFailure: Gagal Bos");

            }
        });
    }
}
