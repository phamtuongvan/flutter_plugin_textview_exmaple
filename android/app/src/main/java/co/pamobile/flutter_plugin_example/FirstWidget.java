package co.pamobile.flutter_plugin_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.platform.PlatformView;

public final class FirstWidget implements PlatformView, MethodCallHandler {
    private final LinearLayout view;
    CurvedText curvedText;
    private final MethodChannel methodChannel;

    public View getView() {
        return this.curvedText;
    }

    public void onMethodCall( MethodCall methodCall, Result result) {

        result.notImplemented();
    }

    private final void ping(MethodCall methodCall, Result result) {
        result.success((Object)null);
    }

    public void dispose() {
    }


    FirstWidget(Context context, BinaryMessenger messenger, int id) {

        view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.cv_widget, (ViewGroup)null);

        curvedText = new CurvedText(context, 1, "minion");
        curvedText.init(3,"minion");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        curvedText.setLayoutParams(params);
        curvedText.setTextOnPath("Hello Card Name",50);


        methodChannel = new MethodChannel(messenger, "plugins/first_widget_" + id);

        methodChannel.setMethodCallHandler(this);
    }
}