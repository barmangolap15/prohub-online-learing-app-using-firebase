package com.example.prohub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.prohub.LoginSignup.StartUpScreen;
import com.example.prohub.Prevelent.Prevalent;
import com.example.prohub.categories.Android;
import com.example.prohub.categories.BigData;
import com.example.prohub.categories.ComputerNetwork;
import com.example.prohub.categories.MachineLearning;
import com.example.prohub.model.CategoriesModel;
import com.example.prohub.model.FeaturedModel;
import com.example.prohub.viewadapter.CategoriesViewHolder;
import com.example.prohub.viewadapter.FeaturedViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;

    private DatabaseReference baseRef, catRef;
    private LinearLayout LinearLayout1, LinearLayout2, LinearLayout3, LinearLayout4;
    private RecyclerView featuredRecyclerView, categoriesRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar, catProgressBar;
    ImageView menuIccon;
    LinearLayout contentView;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        menuIccon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content_view);

        //drawer hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);


        //navigation drawer profile image
        View headerView = navigationView.getHeaderView(0);
        CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);

        Picasso.get().load(Prevalent.currentOnlineUser.getImage()).placeholder(R.drawable.profile_icon).into(profileImageView);

        navigationDrawer();

        //featured courses
        baseRef = FirebaseDatabase.getInstance().getReference().child("Base");
        featuredRecyclerView = findViewById(R.id.featured_recycler);
        featuredRecyclerView.setHasFixedSize(true);
        featuredRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //categories
        catRef = FirebaseDatabase.getInstance().getReference().child("Categories");
        categoriesRecyclerView = findViewById(R.id.categories_recyclerview);
        categoriesRecyclerView.setHasFixedSize(true);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        LinearLayout1 = (LinearLayout) findViewById(R.id.l1);
        LinearLayout2 = (LinearLayout) findViewById(R.id.l2);
        LinearLayout3 = (LinearLayout) findViewById(R.id.l3);
        LinearLayout4 = (LinearLayout) findViewById(R.id.l4);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        catProgressBar = (ProgressBar) findViewById(R.id.cat_progress_bar);
        catProgressBar.setVisibility(View.VISIBLE);



        //functions
        featuredCourses();
        categoriesTech();
    }


    private void featuredCourses() {

        FirebaseRecyclerOptions<FeaturedModel> options = new FirebaseRecyclerOptions.Builder<FeaturedModel>()
                .setQuery(baseRef, FeaturedModel.class)
                .build();
        FirebaseRecyclerAdapter<FeaturedModel, FeaturedViewHolder> adapter = new FirebaseRecyclerAdapter<FeaturedModel, FeaturedViewHolder>(options) {
            @NonNull
            @Override
            public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card, parent, false);
                FeaturedViewHolder holder = new FeaturedViewHolder(view);
                return holder;
            }

            @Override
            protected void onBindViewHolder(@NonNull FeaturedViewHolder holder, final int position, @NonNull final FeaturedModel model) {

                progressBar.setVisibility(View.INVISIBLE);
                holder.baseName.setText(model.getTitle());
                holder.baseDecs.setText(model.getDescription());
                Picasso.get().load(model.getIcon()).into(holder.baseImage);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = v.getContext();
                        Intent intent = new Intent();

                        switch (position) {
                            case 0:
                                intent = new Intent(context, JavaActivity.class);
                                context.startActivity(intent);
                                break;
                            case 1:
                                intent = new Intent(context, CActivity.class);
                                context.startActivity(intent);
                                break;
                            case 2:
                                intent = new Intent(context, CplusActivity.class);
                                context.startActivity(intent);
                                break;
                            case 3:
                                intent = new Intent(context, PythonActivity.class);
                                context.startActivity(intent);
                                break;

                        }
                    }

                });
            }


        };

        featuredRecyclerView.setAdapter(adapter);
        adapter.startListening();

    }

    private void categoriesTech() {

        FirebaseRecyclerOptions<CategoriesModel> options = new FirebaseRecyclerOptions.Builder<CategoriesModel>()
                .setQuery(catRef, CategoriesModel.class)
                .build();
        FirebaseRecyclerAdapter<CategoriesModel, CategoriesViewHolder> catadapter = new FirebaseRecyclerAdapter<CategoriesModel, CategoriesViewHolder>(options) {
            @NonNull
            @Override
            public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_card, parent, false);
                CategoriesViewHolder holder = new CategoriesViewHolder(view);
                return holder;
            }

            @Override
            protected void onBindViewHolder(@NonNull CategoriesViewHolder holder, final int position, @NonNull final CategoriesModel model) {

                catProgressBar.setVisibility(View.INVISIBLE);
                holder.catName.setText(model.getName());
                Picasso.get().load(model.getImage()).into(holder.catImage);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = v.getContext();
                        Intent intent = new Intent();

                        switch (position) {
                            case 0:
                                intent = new Intent(context, Android.class);
                                context.startActivity(intent);
                                break;
                            case 1:
                                intent = new Intent(context, MachineLearning.class);
                                context.startActivity(intent);
                                break;
                            case 2:
                                intent = new Intent(context, BigData.class);
                                context.startActivity(intent);
                                break;
                            case 3:
                                intent = new Intent(context, ComputerNetwork.class);
                                context.startActivity(intent);
                                break;

                        }
                    }

                });
            }


        };

        categoriesRecyclerView.setAdapter(catadapter);
        catadapter.startListening();

    }


    //Navigation Drawer Function
    private void navigationDrawer() {

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIccon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        // drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                break;
            case R.id.nav_technologies:
                startActivity(new Intent(getApplicationContext(), AllTechnologies.class));
                break;
            case R.id.nav_logout:
                Paper.book().destroy();
                Intent intent = new Intent(getApplicationContext(), StartUpScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_setting:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                break;
        }
        return true;
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseRecyclerOptions<FeaturedModel> options = new FirebaseRecyclerOptions.Builder<FeaturedModel>()
//                .setQuery(baseRef, FeaturedModel.class)
//                .build();
//        FirebaseRecyclerAdapter<FeaturedModel, FeaturedViewHolder> adapter = new FirebaseRecyclerAdapter<FeaturedModel, FeaturedViewHolder>(options) {
//            @NonNull
//            @Override
//            public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card, parent, false);
//                FeaturedViewHolder holder = new FeaturedViewHolder(view);
//                return holder;
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull FeaturedViewHolder holder, final int position, @NonNull final FeaturedModel model) {
//
//                progressBar.setVisibility(View.INVISIBLE);
//                holder.baseName.setText(model.getTitle());
//                holder.baseDecs.setText(model.getDescription());
//                Picasso.get().load(model.getIcon()).into(holder.baseImage);
//
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Context context = v.getContext();
//                        Intent intent = new Intent();
//
//                        switch (position) {
//                            case 0:
//                                intent = new Intent(context, JavaActivity.class);
//                                context.startActivity(intent);
//                                break;
//                            case 1:
//                                intent = new Intent(context, CActivity.class);
//                                context.startActivity(intent);
//                                break;
//                            case 2:
//                                intent = new Intent(context, CplusActivity.class);
//                                context.startActivity(intent);
//                                break;
//                            case 3:
//                                intent = new Intent(context, PythonActivity.class);
//                                context.startActivity(intent);
//                                break;
//
//                        }
//                    }
//
//                });
//            }
//
//
//        };
//
//        featuredRecyclerView.setAdapter(adapter);
//        adapter.startListening();
//    }
}