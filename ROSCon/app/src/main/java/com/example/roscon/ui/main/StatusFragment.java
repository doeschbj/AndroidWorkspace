package com.example.roscon.ui.main;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.roscon.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class StatusFragment extends Fragment {
    private static final String ARG_COUNT_STATUS = "count_status";
    private StatusViewModel statusViewModel;
    private Socket socket;

    private static final int SERVERPORT = 50007;
    private static final String SERVER_IP = "192.168.0.110";
    private ArrayList<TextView> text_views;
    private int count = 0;
    private boolean connected = false;

    public static StatusFragment newInstance(int count) {
        StatusFragment fragment = new StatusFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_COUNT_STATUS, count);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusViewModel = new ViewModelProvider(this).get(StatusViewModel.class);

        if (getArguments() != null) {
            count = getArguments().getInt(ARG_COUNT_STATUS);
        }
        statusViewModel.setIndex(count);
        new Thread(new ClientThread()).start();
    }

    class ClientThread implements Runnable {
        @Override
        public void run() {
            while(true) {
                try {
                    InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                    socket = new Socket(serverAddr, SERVERPORT);
                    connected = true;
                    try {
                        PrintWriter out = new PrintWriter(new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream())),
                                true);

                        BufferedReader input;
                        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        out.println("1");
                        input.readLine(); // HIer check

                        out.println("3");
                        String in = input.readLine(); // Hier abfrage nach den Werten
                        String[] splited = in.split(",");
                       // Log.d("Ich_in", "len: " +splited.length);
                        int[] stati = new int[count];
                        for(int j = 0; j < count; j++){
                            stati[j] = Integer.parseInt(splited[j]);
                        }
                        //Formatieren
                        if (text_views != null && count != 0) {
                            for (int i = 0; i < count; i++) {
                                text_views.get(i).setText(getTextFromNumber(i+1, stati[i]));
                            }
                        }

                        input.close();
                        out.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    socket.close();
                    connected = false;
                    Thread.sleep(1000);
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                if(connected){
                    try{
                        socket.close();
                    }catch(IOException e){
                        e.printStackTrace();
                    }

                }
            }
        }

    }
    //@RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.status_frag, container, false);
        LinearLayout linla = root.findViewById(R.id.lin_layout_status);

        if (getArguments() != null) {
            count = getArguments().getInt(ARG_COUNT_STATUS);
        }
        text_views = new ArrayList<TextView>(count);
        for(int i = 0; i < count; i++){
            text_views.add(new TextView(getContext()));
            text_views.get(i).setText("PlaceHolder");
            linla.addView(text_views.get(i));
        }

        return root;
    }

    public String getTextFromNumber(int robot, int nmbr){
        String s = "Robot "+robot;
        if(nmbr == 0){
            s = s + " is inactiv.";
            return s;
        }else if(nmbr == 1){
            s = s + " is moving.";
            return s;
        }else if(nmbr == -2){
            s = s + " waits for starting the execution.";
            return s;
        }else if(nmbr == -3){
            s = "FAILURE";
            return s;
        }
        int mod = nmbr%10;
        int station = (int) nmbr /10;
        if(mod == 1){
            s = s +" is busy at station "+ station+ ".";
            return s;
        }else if(mod==0){
            s = s + " is ready at station "+ station+ ".";
            return s;
        }else{
            s = "Something went wrong";
            return s;
        }
    }
}
