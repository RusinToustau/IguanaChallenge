package app.inthegarden.com.iguanachallenge.io;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HTTPConnectionManager {

	public static boolean isNetworkinOnline (Context context){
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeConnection = cm.getActiveNetworkInfo();
		Boolean isOnline = (activeConnection != null) && activeConnection.isConnected();
		return isOnline;
	}

}
