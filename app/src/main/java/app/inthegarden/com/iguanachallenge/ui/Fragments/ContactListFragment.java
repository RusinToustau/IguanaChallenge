package app.inthegarden.com.iguanachallenge.ui.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import app.inthegarden.com.iguanachallenge.R;
import app.inthegarden.com.iguanachallenge.io.ApiAdapter;
import app.inthegarden.com.iguanachallenge.io.HTTPConnectionManager;
import app.inthegarden.com.iguanachallenge.pojo.Contact;
import app.inthegarden.com.iguanachallenge.ui.Adapters.ContactAdapter;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment {
    private List<Contact> contacts;
    private RecyclerView recycler;
    private ContactAdapter adapter;
    Notify notify;

    public ContactListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        recycler = view.findViewById(R.id.recycler_contacts);

        if(HTTPConnectionManager.isNetworkinOnline(getContext())){
            Call<List<Contact>> call = ApiAdapter.getApiService().getContacts();
            call.enqueue(new ContactResponce());
        } else {
            Toast.makeText(getContext(),R.string.connection_lost,Toast.LENGTH_LONG).show();
            onStop();
        }
        return view;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Integer position = recycler.getChildAdapterPosition(v);
            String user_id = adapter.selected(position).getUserId();

            notify = (Notify) getActivity();
            notify.goToDescription(user_id);
        }
    };

    private class ContactResponce implements retrofit2.Callback<List<Contact>> {
        @Override
        public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
            contacts = response.body();
            adapter = new ContactAdapter(contacts);
            recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            adapter.setListener(listener);
            recycler.setAdapter(adapter);
        }

        @Override
        public void onFailure(Call<List<Contact>> call, Throwable t) {
            Log.d("Error",t.getMessage());
        }
    }

    public interface Notify{
        void goToDescription(String user_id);
    }
}
