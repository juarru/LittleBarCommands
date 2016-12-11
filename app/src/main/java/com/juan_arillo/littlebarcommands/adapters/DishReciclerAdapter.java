package com.juan_arillo.littlebarcommands.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juan_arillo.littlebarcommands.R;
import com.juan_arillo.littlebarcommands.models.Dish;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

import static com.juan_arillo.littlebarcommands.R.id.dish_image;


public class DishReciclerAdapter extends RecyclerView.Adapter<DishReciclerAdapter.DishViewHolder>{

    private LinkedList<Dish> mDish;
    private OnDishSelectedListener mDishSelectedListener;

    public DishReciclerAdapter(LinkedList<Dish> dish, OnDishSelectedListener dishSelectedListener){
        mDish = dish;
        mDishSelectedListener = dishSelectedListener;
    }

    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_dish, parent,false);
        return new DishViewHolder(view, mDishSelectedListener);
    }

    @Override
    public void onBindViewHolder(DishViewHolder holder, int position) {
        holder.bindCourse(mDish.get(position));
    }

    @Override
    public int getItemCount() {
        return mDish.size();
    }

    public class DishViewHolder extends RecyclerView.ViewHolder{
        private Dish mDish;
        private WeakReference<OnDishSelectedListener> mOnDishSelectedListener;

        private TextView mName;
        private TextView mPrice;
        private TextView mDescription;
        private ImageView mImage;
        private LinearLayout mLinearLayout;

        public DishViewHolder(View itemView, OnDishSelectedListener dishSelectedListener) {
            super(itemView);
            mOnDishSelectedListener = new WeakReference<>(dishSelectedListener);

            mName = (TextView) itemView.findViewById(R.id.dish_name);
            mImage = (ImageView) itemView.findViewById(dish_image);
            mDescription = (TextView) itemView.findViewById(R.id.dish_description);
            mPrice = (TextView) itemView.findViewById(R.id.dish_price);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.dish_allergens);
        }

        public void bindCourse(final Dish dish){
            String units = " €";
            mDish = dish;
            mName.setText(dish.getName());
            mDescription.setText(dish.getDescription());

            if ( mDish.getAllergens().size() > 0) {
                mLinearLayout.removeAllViews();
                for (int i = 0; i < mDish.getAllergens().size(); i++) {
                    String allergen = mDish.getAllergens().get(i);
                    ImageView imageView = new ImageView(this.itemView.getContext());
                    switch (allergen)
                    {
                        case "01":
                            imageView.setImageResource(R.drawable.aler_01);
                            break;
                        case "02":
                            imageView.setImageResource(R.drawable.aler_02);
                            break;
                        case "03":
                            imageView.setImageResource(R.drawable.aler_03);
                            break;
                    }
                    mLinearLayout.addView(imageView);
                }
            }

            mPrice.setText(String.format("PRECIO: %.2f %s", dish.getPrice(), units));


            String image = mDish.getImage();
            int foodImage = 0;
            switch (image){
                case "01":
                    foodImage = R.drawable.dish_01;
                    break;
                case "02":
                    foodImage = R.drawable.dish_02;
                    break;
                case "03":
                    foodImage = R.drawable.dish_03;
                    break;
            }
            mImage.setImageResource(foodImage);
            mImage.setVisibility(View.VISIBLE);


            if (mOnDishSelectedListener.get() != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mDish != null) {
                            mOnDishSelectedListener.get().onDishSelected(mDish);
                        }
                    }
                });

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        if (mDish != null) {
                            mOnDishSelectedListener.get().onDishLongSelected(mDish);
                        }
                        return true;
                    }
                });
            }
        }
    }

    public interface OnDishSelectedListener {
        void onDishSelected(Dish dish);
        void onDishLongSelected(Dish dish);
    }

}
