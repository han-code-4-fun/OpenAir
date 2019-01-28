package utils;

import android.content.Context;

import androidx.core.content.ContextCompat;
import project.examlple.com.openair.R;

public class ColorUtils {

    //class designed to return different color based on polution type and its value

    public static int getColor(Context context, double value, String paramType)
    {
        int level = getLevelFromParamTypeNValue(value, paramType);
        return getViewHolderBackgroundColorFromLevel(context,level);
    }


    public static int getViewHolderBackgroundColorFromLevel(Context context, int value) {
        switch (value) {
            case 1:
                return ContextCompat.getColor(context, R.color.color_level_1);
            case 2:
                return ContextCompat.getColor(context, R.color.color_level_2);
            case 3:
                return ContextCompat.getColor(context, R.color.color_level_3);
            case 4:
                return ContextCompat.getColor(context, R.color.color_level_4);
            case 5:
                return ContextCompat.getColor(context, R.color.color_level_5);
            case 6:
                return ContextCompat.getColor(context, R.color.color_level_6);
            case 0:
                return ContextCompat.getColor(context, R.color.color_level_na);
            default:
                return ContextCompat.getColor(context, R.color.color_level_default);
        }
    }

    private  static int getLevelFromParamTypeNValue(double v, String paramType)
    {
        int level = -1;
        int value = (int)Math.round(v);

        switch (paramType){

            case "SO2":
                level = getLevel(value,40,80,380,800,1600);
                break;
            case "PM25":
                level = getLevel(value,30,60,90,120,250);
                break;
            case "PM10":
                level = getLevel(value, 50,100,250,350,430);
                break;
            case "O3":
                level = getLevel(value,50,100,168,208,748);
                break;
            case "NO2":
                level = getLevel(value,40,80,180,280,400);
                break;
            case "CO":
                level = getLevel(value,50,100,200,300,400);
                break;
            //Don't find a color level for this value
            case "BC":
                level = -1;
                break;

        }


        return level;

    }

    private static int getLevel(int value, int l1, int l2, int l3, int l4, int l5){
        if(value<=l1)
        {
            return 1;
        }
        if(value<=l2)
        {
            return 2;
        }
        if(value<=l3)
        {
            return 3;
        }
        if(value<=l4)
        {
            return 4;
        }
        if(value<=l5)
        {
            return 5;
        }else
        {
            return 6;
        }

    }


}
