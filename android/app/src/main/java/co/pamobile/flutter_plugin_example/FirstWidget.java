package co.pamobile.flutter_plugin_example;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.platform.PlatformView;

public final class FirstWidget implements PlatformView {

    CurvedText curvedText;
    public View getView() {
        return this.curvedText;
    }

    @Override
    public void dispose() {
    }

    FirstWidget(Context context, BinaryMessenger messenger, int id) {
        curvedText = new CurvedText(context, 1, "minion");
        curvedText.init(3,"minion");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        curvedText.setLayoutParams(params);
        curvedText.setTextOnPath("Hello Card Name",50);
        Log.e("EEE","plugins/first_widget_"+id);
        (new MethodChannel(messenger, "plugins/first_widget_" + id)).setMethodCallHandler((new MethodChannel.MethodCallHandler() {
            public final void onMethodCall(MethodCall call, MethodChannel.Result result) {
                Object arg;
                if (call.method.equals( "ping")) {
                    result.success("ping success");
                }else{
                    result.notImplemented();
                }
            }
        }));
    }
}