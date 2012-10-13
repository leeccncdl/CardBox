package cn.zytec.lee.act;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import cn.zytec.lee.CardBoxApp;
import cn.zytec.lee.gallery.GalleryFlow;
import cn.zytec.lee.gallery.ImageAdapter;

public class MainActivity extends Activity {
	private ImageAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Integer[] images = { R.drawable.img0001, R.drawable.img0030,
                R.drawable.img0100, R.drawable.img0130, R.drawable.img0200,
                R.drawable.img0230,  R.drawable.img0330,R.drawable.img0354};
        
        adapter = new ImageAdapter(this, images);
        upDateView();
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /**
     * 更新视图
     * 根据显示视图的静态变量，决定显示 画廊视图或者表格视图
     */
    private void upDateView() {
        switch (CardBoxApp.showView) {
		case CardBoxApp.GALLERYVIEW:
	        GalleryFlow galleryFlow = (GalleryFlow) this.findViewById(R.id.Gallery);
	        galleryFlow.setFadingEdgeLength(0);
	        galleryFlow.setSpacing(-100); //图片之间的间距
	        galleryFlow.setAdapter(adapter);
	        
	        galleryFlow.setOnItemClickListener(new OnItemClickListener() {
	            public void onItemClick(AdapterView<?> parent, View view,
	                    int position, long id) {
	                Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
	            }
	            
	        });
	        galleryFlow.setSelection(0);
			break;
		case CardBoxApp.GRIDVIEW:
			
			break;
		default:
			break;
		}
    }
}
