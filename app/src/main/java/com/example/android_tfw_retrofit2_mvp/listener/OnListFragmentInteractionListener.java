package com.example.android_tfw_retrofit2_mvp.listener;

import com.example.android_tfw_retrofit2_mvp.activity.main.frgments.dummy.DummyContent;

/**
 * Created by 李均 on 2016/11/22.
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p/>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */

public interface OnListFragmentInteractionListener {
    // TODO: Update argument type and name
    void onListFragmentInteraction(DummyContent.DummyItem item);
}
