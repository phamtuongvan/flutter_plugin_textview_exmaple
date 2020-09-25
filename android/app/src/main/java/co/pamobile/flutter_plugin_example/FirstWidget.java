package co.pamobile.flutter_plugin_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.platform.PlatformView;

public final class FirstWidget implements PlatformView, MethodCallHandler {
    private final View view;
    private final MethodChannel methodChannel;

    public View getView() {
        return this.view;
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
        view = LayoutInflater.from(context).inflate(R.layout.first_widget, (ViewGroup)null);

        methodChannel = new MethodChannel(messenger, "plugins/first_widget_" + id);

        methodChannel.setMethodCallHandler(this);
    }
}