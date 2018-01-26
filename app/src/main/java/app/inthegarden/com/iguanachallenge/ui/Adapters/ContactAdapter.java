package app.inthegarden.com.iguanachallenge.ui.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.inthegarden.com.iguanachallenge.R;
import app.inthegarden.com.iguanachallenge.pojo.Contact;


/**
 * Created by Felix on 24/01/2018.
 */

public class ContactAdapter extends RecyclerView.Adapter implements View.OnClickListener{
    private List<Contact> list;
    private View.OnClickListener listener;

    public ContactAdapter(List<Contact> list) {
        this.list = list;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_contact, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.uploadView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public Contact selected(Integer position){
        return list.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView first_name;
        private TextView last_name;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            last_name = itemView.findViewById(R.id.cv_tv_last_name);
            first_name = itemView.findViewById(R.id.cv_tv_name);
            imageView = itemView.findViewById(R.id.cv_iv_image);
        }

        public void uploadView(Contact contact) {
            first_name.setText("Nombre: " + contact.getFirstName());
            last_name.setText("Apellido: " + contact.getLastName());
            Glide.with(itemView.getContext())
                    .load(contact.getThumb())
                    .error(R.drawable.default_profile)
                    .into(imageView);
        }
    }
}
