package com.androidproject.PhoneGarage.Fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidproject.PhoneGarage.JavaBeans.Consts;
import com.androidproject.PhoneGarage.R;

/**
 * @author Ghaith Darwish
 */
public class DevelopersFragment extends Fragment {

    public static final int PERMISSION_CALL = 2;
    public static final int PERMISSION_SEND_SMS = 1;

    ImageView developerImage;
    Button webButton;
    Button emailButton;
    Button facButton;
    Button callButton;
    Button locaButton;
    Button textButton;
    Button addToContactButton;

    public static String phone;
    int image;
    String website;
    String[] email;
    String facebook;
    String location;
    String addToContact;

    public DevelopersFragment() {
        // Required empty public constructor
    }

    /**
     * @param savedInstanceState
     * @author Ghaith Darwish
     * @description Receiving data from the bundle argument and using it to complete intents
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image = getArguments().getInt(Consts.image, 0);
            website = getArguments().getString(Consts.website, "");
            email = getArguments().getStringArray(Consts.email);
            facebook = getArguments().getString(Consts.facebook, "");
            phone = getArguments().getString(Consts.phone, "");
            location = getArguments().getString(Consts.location, "");
            addToContact = getArguments().getString(Consts.addToContact, "");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_developers, container, false);

        developerImage = view.findViewById(R.id.developer_img);
        developerImage.setImageResource(image);

        // Create a web Intent
        webButton = view.findViewById(R.id.webButton);
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = website;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        // Create a email Intent
        emailButton = view.findViewById(R.id.emailButton);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Information");
                intent.putExtra(Intent.EXTRA_TEXT, "I would like to get more info about.....");
                startActivity(intent);
            }
        });

        // create a facebook Intent
        facButton = view.findViewById(R.id.facButton);
        facButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(facebook));
                startActivity(i);
            }
        });

        // Create a call Intent
        callButton = view.findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if you have the permission or not
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // Gets whether you should show UI with rationale for requesting permission or not
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE)) {
                        final AlertDialog alertDialogCall = new AlertDialog.Builder(getContext())
                                .setTitle("Make a phone call")
                                .setMessage("We need the access to make the phone call!")
                                .create();
                        // Create a OK button on the AlertDialog and set a even listener on it
                        alertDialogCall.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialogCall.dismiss();
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_CALL);
                            }
                        });
                        // show the alert
                        alertDialogCall.show();
                    } else {
                        // ask for permission
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_CALL);
                    }
                } else {
                    // start the intent
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    // check if phone support this kind of activity
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        // if phone doesn't support it
                        Toast.makeText(getContext(), "No software installed to complete task", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Create a location Intent
        locaButton = view.findViewById(R.id.locaButton);
        locaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri locationUri = Uri.parse(location);
                Intent intent = new Intent(Intent.ACTION_VIEW, locationUri);
                startActivity(intent);
            }
        });

        // Create a text Intent
        textButton = view.findViewById(R.id.textButton);
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if you have the permission or not
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    // Gets whether you should show UI with rationale for requesting permission or not
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.SEND_SMS)) {
                        final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                                .setTitle("Send sms")
                                .setMessage("We need the access to send the sms!")
                                .create();
                        // Create a OK button on the AlertDialog and set a even listener on it
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog.dismiss();
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, PERMISSION_SEND_SMS);
                            }
                        });
                        // show the alert
                        alertDialog.show();
                    } else {
                        // ask for permission
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, PERMISSION_SEND_SMS);
                    }
                } else {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("sms", phone, null));
                    intent.putExtra("sms_body", "I would like to get some information about....");
                    // check if phone support this kind of activity
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        // if phone doesn't support it
                        Toast.makeText(getContext(), "No software installed to complete task", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // create addToContact Intent
        addToContactButton = view.findViewById(R.id.addToContactButton);
        addToContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone).putExtra(ContactsContract.Intents.Insert.PHONE_TYPE,
                        ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
                startActivity(intent);
            }
        });

        return view;
    }

}