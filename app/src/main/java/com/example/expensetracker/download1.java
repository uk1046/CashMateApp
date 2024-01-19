package com.example.expensetracker;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class download1 extends AppCompatActivity {
    ImageButton btn;
    RadioButton r1, r2, r3, r4, r5, r6;
    Button b1;
    RadioGroup rg;
    private DbHandler dbHandler;
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "ExpenseTrackerChannel";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download1);

        rg = findViewById(R.id.rgb);
        dbHandler = new DbHandler(this);  // Initialize dbHandler

        int id001 = rg.getCheckedRadioButtonId();
        btn = findViewById(R.id.back);
        b1 = findViewById(R.id.download);
        r1 = findViewById(R.id.rb1);
        r2 = findViewById(R.id.rb2);
        r3 = findViewById(R.id.rb3);
        r4 = findViewById(R.id.rb4);
        r5 = findViewById(R.id.rb5);
        r6 = findViewById(R.id.rb6);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = dateFormat.format(calendar.getTime());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(download1.this, InterFace.class);
                startActivity(i1);
                finish();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromDate = "";

                if (r1.isChecked()) {
                    fromDate = currentDate;
                } else if (r2.isChecked()) {
                    calendar.add(Calendar.DAY_OF_MONTH, -7);
                    fromDate = dateFormat.format(calendar.getTime());
                } else if (r3.isChecked()) {
                    calendar.add(Calendar.MONTH, -1);
                    fromDate = dateFormat.format(calendar.getTime());
                } else if (r4.isChecked()) {
                    calendar.add(Calendar.MONTH, -3);
                    fromDate = dateFormat.format(calendar.getTime());
                } else if (r5.isChecked()) {
                    calendar.add(Calendar.MONTH, -6);
                    fromDate = dateFormat.format(calendar.getTime());
                } else if (r6.isChecked()) {
                    calendar.add(Calendar.YEAR, -1);
                    fromDate = dateFormat.format(calendar.getTime());
                } else {
                    Toast.makeText(download1.this, "Select a date range", Toast.LENGTH_SHORT).show();
                    return; // Do not proceed if no date range is selected
                }

                createPdf(fromDate, currentDate); // Pass fromDate and currentDate to createPdf function
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    showNotification();
                }

                Intent i1 = new Intent(download1.this, InterFace.class);
                startActivity(i1);
                finish();
            }
        });
    }

    private void createPdf(String fromDate, String currentDate) {
        ArrayList<HashMap<String, String>> recordsList = dbHandler.GetUsersWithinDateRange(fromDate, currentDate);

        if (recordsList.size() > 0) {
            // Calculate column widths dynamically based on content
            float[] columnWidths = new float[]{150, 150, 100, 150, 100, 200}; // Adjust the widths as needed
            float margin = 18.0f; // Margin in points, 1cm = 28.35 points
            int recordsPerPage = 20; // Number of records to display per page

            // Calculate total width for the page
            int totalWidth = 0;
            for (float width : columnWidths) {
                totalWidth += width;
            }

            // Calculate number of pages needed
            int totalPages = (int) Math.ceil((double) recordsList.size() / recordsPerPage);

            // Create a new PdfDocument
            PdfDocument pdfDocument = new PdfDocument();

            for (int currentPage = 0; currentPage < totalPages; currentPage++) {
                // Create a page
                PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder((int) (totalWidth + 2 * margin), 800, currentPage + 1).create();
                PdfDocument.Page page = pdfDocument.startPage(pageInfo);

                // Get the canvas of the page
                Canvas canvas = page.getCanvas();

                // Create a paint object for drawing
                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setTextSize(14); // Text size for table content
                paint.setTypeface(Typeface.create(Typeface.SERIF, Typeface.NORMAL)); // Set font family to serif

                int yPos = 50;

                // Draw centered heading with current date
                paint.setTextAlign(Paint.Align.CENTER);
                paint.setTextSize(31); // Increased text size for heading by 10%
                paint.setFakeBoldText(true); // Make text bold
                canvas.drawText("Transaction Record From " + fromDate + " to " + currentDate, totalWidth / 2 + margin, yPos, paint);
                paint.setTextSize(14); // Reset text size for the rest of the content
                paint.setFakeBoldText(false); // Reset bold style
                paint.setTextAlign(Paint.Align.LEFT);
                yPos += 45; // Increased margin for header

                // Draw table header with borders
                String[] headers = new String[]{"Transaction Type", "Method", "Amount", "Date", "Time", "Note"};
                drawTableRowWithBorders(canvas, paint, yPos, headers, columnWidths, margin);
                yPos += 20;

                // Draw records for the current page
                int startIndex = currentPage * recordsPerPage;
                int endIndex = Math.min((currentPage + 1) * recordsPerPage, recordsList.size());

                for (int i = startIndex; i < endIndex; i++) {
                    HashMap<String, String> record = recordsList.get(i);
                    String[] rowData = new String[]{
                            record.get("type"),
                            record.get("method"),
                            record.get("amount"),
                            record.get("date"),
                            record.get("time"),
                            record.get("note")
                    };

                    drawTableRowWithBorders(canvas, paint, yPos, rowData, columnWidths, margin);
                    yPos += 20;
                }

                // Draw copyright logo
                paint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText("© CashMate", totalWidth / 2 + margin, pageInfo.getPageHeight() - 20, paint);

                // Draw page number
                paint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText("Page " + (currentPage + 1), totalWidth + margin - 20, pageInfo.getPageHeight() - 20, paint);

                // Finish the page
                pdfDocument.finishPage(page);
            }

            // Save the PDF file in Documents directory
            File documentsDirectory = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            String pdfFileName = "records_" + getCurrentTimestamp() + ".pdf";
            File pdfFile = new File(documentsDirectory, pdfFileName);

            Log.d("PDF Path", pdfFile.getAbsolutePath());

            try {
                pdfDocument.writeTo(new FileOutputStream(pdfFile));
                Toast.makeText(this, "PDF created successfully: " + pdfFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error creating PDF", Toast.LENGTH_SHORT).show();
            }

            // Close the document
            pdfDocument.close();
        } else {
            Toast.makeText(this, "No records available to create PDF", Toast.LENGTH_SHORT).show();
        }
    }

    private void drawTableRowWithBorders(Canvas canvas, Paint paint, int yPos, String[] rowData, float[] columnWidths, float margin) {
        float xPos = margin;
        float cellHeight = 20;

        // Draw top border
        canvas.drawLine(xPos, yPos, xPos + calculateTotalWidth(columnWidths), yPos, paint);

        for (int i = 0; i < rowData.length; i++) {
            // Draw left border
            canvas.drawLine(xPos, yPos, xPos, yPos + cellHeight, paint);
            // Draw cell content
            canvas.drawText(rowData[i], xPos + 5, yPos + 15, paint);
            // Move to the next cell
            xPos += columnWidths[i];
            // Draw right border
            canvas.drawLine(xPos, yPos, xPos, yPos + cellHeight, paint);
        }

        // Draw bottom border
        canvas.drawLine(margin, yPos + cellHeight, xPos, yPos + cellHeight, paint);
    }

    private float calculateTotalWidth(float[] columnWidths) {
        float totalWidth = 0;
        for (float width : columnWidths) {
            totalWidth += width;
        }
        return totalWidth;
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        return sdf.format(new Date());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void showNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "ExpenseTrackerChannel";
            String description = "Expense Tracker Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
            Log.d("Notification", "Channel created");
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_file_download_24)
                .setContentTitle("Cashmate")
                .setContentText("PDF download completed.")
                .setAutoCancel(true);

        // Add an action to the notification to open the PDF
        Intent openPdfIntent = new Intent(Intent.ACTION_VIEW);
        openPdfIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        openPdfIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        File documentsDirectory = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        String pdfFileName = "records_" + getCurrentTimestamp() + ".pdf";
        File pdfFile = new File(documentsDirectory, pdfFileName);
        Uri pdfUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", pdfFile);

        openPdfIntent.setDataAndType(pdfUri, "application/pdf");
        PendingIntent openPdfPendingIntent = PendingIntent.getActivity(this, 0, openPdfIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Add the action to the builder
        builder.addAction(new NotificationCompat.Action(R.drawable.baseline_folder_open, "Open PDF", openPdfPendingIntent));

        // Set the content intent (when clicking on the main body of the notification)
        builder.setContentIntent(openPdfPendingIntent);

        // Notify
        notificationManager.notify(NOTIFICATION_ID, builder.build());
        Log.d("Notification", "Notification sent");
    }
}
