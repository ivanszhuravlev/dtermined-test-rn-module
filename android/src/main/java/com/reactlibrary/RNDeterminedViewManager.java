package com.reactlibrary;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

//import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Map;

import javax.annotation.Nullable;

import static java.security.AccessController.getContext;


public class RNDeterminedViewManager extends SimpleViewManager<Button> {
    public static final String REACT_CLASS = "DterminedView";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public Button createViewInstance(ThemedReactContext reactContext) {
        final Button but = new Button(reactContext);

        but.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                clickCallback(v);
            }
        });

        return but;
    }

    @ReactProp(name = "text")
    public void setText(Button but, String text) {
        but.setText(text);
    }

    public void clickCallback(View view) {
        WritableMap event = Arguments.createMap();

        event.putString("value1","react demo");
        ReactContext ctx = (ReactContext) view.getContext();
        ctx.getJSModule(RCTEventEmitter.class).receiveEvent(view.getId(), "onClickEvent", event);
    }

    public Map getExportedCustomBubblingEventTypeConstants() {
        String eventName = "onClickEvent";
        String propName = "onClick";
        Map onClickHandler = MapBuilder.of("phasedRegistrationNames",MapBuilder.of("bubbled", propName));

        MapBuilder.Builder events = MapBuilder.builder();
        events.put(eventName, onClickHandler);
        return events.build();
    }

}