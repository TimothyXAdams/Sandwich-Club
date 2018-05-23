package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;
import android.view.View;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.utils.JsonUtils;
import com.udacity.sandwichclub.model.Sandwich;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        TextView akaTextView = findViewById(R.id.alsoKnownAsTextView);
        TextView akaTextViewLabel = findViewById(R.id.textView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        TextView descriptionTextViewLabel = findViewById(R.id.textView4);
        TextView originTextView = findViewById(R.id.originTextView);
        TextView originTextViewLabel = findViewById(R.id.textView3);
        TextView ingredientsTextView = findViewById(R.id.ingredients);
        TextView ingredientsTextViewLabel = findViewById(R.id.textView2);



        if (sandwich.getAlsoKnownAs() == null) {
            akaTextView.setVisibility(View.GONE);
            akaTextViewLabel.setVisibility(View.GONE);
        } else {
            akaTextView.setText(android.text.TextUtils.join(", ", sandwich.getAlsoKnownAs()));
        }



        if (sandwich.getDescription().isEmpty()) {
            descriptionTextView.setVisibility(View.GONE);
            descriptionTextViewLabel.setVisibility(View.GONE);
        } else {
            descriptionTextView.setText(sandwich.getDescription());
        }


        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            originTextView.setVisibility(View.GONE);
            originTextViewLabel.setVisibility(View.GONE);
        } else {
            originTextView.setText(sandwich.getPlaceOfOrigin());
        }



        if (sandwich.getIngredients() == null) {
            ingredientsTextView.setVisibility(View.GONE);
            ingredientsTextViewLabel.setVisibility(View.GONE);
        } else {
            ingredientsTextView.setText(android.text.TextUtils.join(", ", sandwich.getIngredients()));
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == 0) {
            Toast.makeText(this, R.string.id_error_message, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
