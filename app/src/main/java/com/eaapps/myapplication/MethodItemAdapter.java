package com.eaapps.myapplication;

import android.graphics.Color;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eaapps.myapplication.databinding.ItemPaymentMethodBinding;

import java.util.List;

public class MethodItemAdapter extends RecyclerView.Adapter<MethodItemAdapter.ItemViewHolder> {

    private final List<Cards> cardsList;
    private final SelectCard selectCard;

    public MethodItemAdapter(List<Cards> cardsList, SelectCard selectCard) {
        this.cardsList = cardsList;
        this.selectCard = selectCard;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(ItemPaymentMethodBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return cardsList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemPaymentMethodBinding itemPaymentMethodBinding;

        public ItemViewHolder(@NonNull ItemPaymentMethodBinding itemPaymentMethodBinding) {
            super(itemPaymentMethodBinding.getRoot());
            this.itemPaymentMethodBinding = itemPaymentMethodBinding;

        }

        private void clearSelector() {
            for (int i = 0; i < cardsList.size(); i++) {
                Cards cards = cardsList.get(i);
                cards.setSelected(false);
                cardsList.set(i, cards);
            }
        }

        private String addSpace(String s) {
            StringBuilder retVal;

            if (s.length() <= 4) {
                return s;
            }

            retVal = new StringBuilder(s);
            for (int i = retVal.length(); i > 0; i -= 4) {
                retVal.insert(i, " ");
            }
            return retVal.toString();
        }

        void bind(int position) {
            itemPaymentMethodBinding.imageVisa.setImageResource(cardsList.get(position).image);
            itemPaymentMethodBinding.visaNumber.setText(addSpace(cardsList.get(position).number));
            itemPaymentMethodBinding.itemBg.setBackgroundResource(cardsList.get(position).isSelected() ? R.drawable.bg_border_green_corner_8 : R.drawable.bg_white_unselected_rectangle);
            itemPaymentMethodBinding.radioButton.setChecked(cardsList.get(position).isSelected());
            itemPaymentMethodBinding.visaNumber.setTextColor(cardsList.get(position).isSelected() ? Color.parseColor("#ff48b976") : Color.parseColor("#000000"));
            itemPaymentMethodBinding.visaNumber.setTransformationMethod(new PasswordTransformationMethod() {
                @Override
                public CharSequence getTransformation(CharSequence source, View view) {
                    return new CharSequence() {
                        @Override
                        public int length() {
                            return source.length();
                        }

                        @Override
                        public char charAt(int index) {
                            char character;
                            switch (index) {
                                case 4:
                                case 14:
                                case 9:
                                    character = ' ';
                                    break;
                                default:
                                    if (index < 15)
                                        return '●';
                                    else
                                        character = source.charAt(index);
                                    break;
                            }


                            return character;
                        }

                        @NonNull
                        @Override
                        public CharSequence subSequence(int start, int end) {
                            return source.subSequence(start, end);
                        }
                    };
                }
            });
            itemView.setOnClickListener(v -> {
                clearSelector();
                Cards cards = cardsList.get(position);
                cards.number=itemPaymentMethodBinding.visaNumber.getText().toString();
                cards.setSelected(!cards.isSelected());
                cardsList.set(position, cards);
                notifyDataSetChanged();
                selectCard.card(cards);
            });
        }
    }

    public interface SelectCard{
        void card(Cards cards);
    }
}
