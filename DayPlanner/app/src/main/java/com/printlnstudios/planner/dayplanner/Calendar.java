package com.printlnstudios.planner.dayplanner;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Calendar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Calendar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calendar extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Calendar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Calendar.
     */
    // TODO: Rename and change types and number of parameters
    public static Calendar newInstance(String param1, String param2) {
        Calendar fragment = new Calendar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        Integer[] id_numbers = {R.id.time_zero , R.id.time_one, R.id.time_two, R.id.time_three,
                R.id.time_four, R.id.time_five, R.id.time_six, R.id.time_seven, R.id.time_eight,
                R.id.time_nine, R.id.time_ten, R.id.time_eleven, R.id.time_twelve, R.id.time_thirteen,
                R.id.time_fourteen, R.id.time_fifteen, R.id.time_sixteen, R.id.time_seventeen,
                R.id.time_eighteen, R.id.time_nineteen, R.id.time_twenty, R.id.time_twentyone,
                R.id.time_twentytwo, R.id.time_twentythree};

        for(int i = 0; i < id_numbers.length; i++) {
            View time_devider = view.findViewById(id_numbers[i]);
            TextView time_text = (TextView) time_devider.findViewById(R.id.time_text);
            time_text.setText("" + (((i + 11) % 12) + 1));
        }

        addEvents(view);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void addEvents(View v) {

        float time = 10;
        float length = 1f;

        for(int i = 0; i < 24; i+= (int)(Math.random() * 3) + 1) {
            time = i;

            //Get the layout of the event and set its murgins such that the event is placed at the right time
            RelativeLayout event_layout = (RelativeLayout) v.findViewById(R.id.events);
            View new_event = getLayoutInflater(null).inflate(R.layout.event_layout, null);
            event_layout.addView(new_event);


            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );

            params.setMargins((int) dptopx(32), (int) dptopx((32.29f * time) + .625f), 0, 0);
            new_event.setLayoutParams(params);

            //Get the image view in the event and set its height so the event has the right length
            //TODO set the width based on the fragment width and the number of overlapping events
            ImageView image = (ImageView) event_layout.findViewById(R.id.event_image);

            ViewGroup.LayoutParams par = (ViewGroup.LayoutParams) image.getLayoutParams();
            par.height *= length;
            image.setLayoutParams(par);
        }

    }

    public float dptopx(float sizeInDP){
        return sizeInDP * getContext().getResources().getDisplayMetrics().density;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
