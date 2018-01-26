package app.inthegarden.com.iguanachallenge.ui.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import app.inthegarden.com.iguanachallenge.R;
import app.inthegarden.com.iguanachallenge.io.ApiAdapter;
import app.inthegarden.com.iguanachallenge.io.HTTPConnectionManager;
import app.inthegarden.com.iguanachallenge.pojo.Address;
import app.inthegarden.com.iguanachallenge.pojo.Detail;
import app.inthegarden.com.iguanachallenge.pojo.Phone;
import app.inthegarden.com.iguanachallenge.ui.CircleTransform;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    public static final String USERID ="user_id";
    private String user_id;
    private TextView tv_name;
    private TextView tv_home_phone;
    private TextView tv_office_phone;
    private TextView tv_cell_phone;
    private TextView tv_birth_day;
    private TextView tv_created;
    private TextView tv_work_location;
    private TextView tv_home_location;
    private ImageView imageView;

    public DetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        user_id = bundle.getString(USERID);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        /*Define all items view*/
        tv_name=view.findViewById(R.id.fd_tv_name);
        imageView = view.findViewById(R.id.fd_iv);
        tv_home_phone = view.findViewById(R.id.fd_tv_home_phone);
        tv_office_phone = view.findViewById(R.id.fd_tv_home_phone);
        tv_cell_phone = view.findViewById(R.id.fd_tv_cell_phone);
        tv_birth_day = view.findViewById(R.id.fd_tv_birth);
        tv_created = view.findViewById(R.id.fd_tv_created);
        tv_home_location = view.findViewById(R.id.fd_tv_home_location);
        tv_work_location = view.findViewById(R.id.fd_tv_work_location);

        user_id = bundle.getString(USERID);

        if (HTTPConnectionManager.isNetworkinOnline(getContext())){
            Call<Detail> call = ApiAdapter.getApiService().getDetail(user_id);
            call.enqueue(new DetailRespose());
        } else {
            Toast.makeText(getContext(),R.string.connection_lost,Toast.LENGTH_LONG).show();
            onStop();
        }
        return view;
    }

    private class DetailRespose implements retrofit2.Callback<Detail> {
        @Override
        public void onResponse(Call<Detail> call, Response<Detail> response) {
            Detail detail = response.body();
            tv_name.setText(detail.getFirstName()+" "+detail.getLastName());
            tv_birth_day.setText(formatDate(detail.getBirthDate()));
            tv_created.setText(formatDate(detail.getCreatedAt()));
            Glide.with(getContext())
                    .load(detail.getPhoto())
                    .error(R.drawable.default_profile)
                    .transform(new CircleTransform(getContext()))
                    .into(imageView);
            List<Phone> phones = detail.getPhones();
            setPhones(phones);
            List<Address> addresses = detail.getAddresses();
            setAddress(addresses);
        }

        @Override
        public void onFailure (Call<Detail> call, Throwable t) {
            Log.d("Error",t.getMessage());
        }
    }

    public void setPhones (List<Phone> phones) {
        for (Phone phone : phones) {
            if (phone.getType().equals("Home")) {
                try {
                    tv_home_phone.setText(phone.getNumber());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (phone.getType().equals("Cellphone")) {
                try {
                    tv_cell_phone.setText(phone.getNumber());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    tv_office_phone.setText(phone.getNumber());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setAddress(List<Address> Addresses){
        for (Address address : Addresses) {
            try {
                if (address.getHome() != null) {
                    tv_work_location.setText(address.getWork());}
                else {tv_work_location.setText(R.string.not_found);}
            } catch (Exception e) {
                tv_work_location.setText(R.string.not_found);
                e.printStackTrace();
            }
            try {
                if (address.getHome() != null) {
                tv_home_location.setText(address.getHome());}
                else {tv_home_location.setText(R.string.not_found);}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String formatDate (String day) {
        try {
            day = day.substring(0,10) ;
            SimpleDateFormat parsedate = new SimpleDateFormat("yy-MM-dd");
            SimpleDateFormat formatdate = new SimpleDateFormat("MM/dd/yyyy");
            Date date = parsedate.parse(day);
            day = formatdate.format(date).toString();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return day;
    }
}
