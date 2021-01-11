/**
 * Copyright 2014 John Persano
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mylibrary.utils.ext;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.utils.R;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * SuperToasts are designed to replace stock Android Toasts.
 * If you need to display a SuperToast inside of an Activity
 */
@SuppressWarnings("UnusedDeclaration")
public class SuperToast {

    private static final String TAG = "SuperToast";

    private static final String ERROR_CONTEXTNULL = " - You cannot use a null context.";
    private static final String ERROR_DURATIONTOOLONG = " - You should NEVER specify a duration greater than " +
            "four and a half seconds for a SuperToast.";

    /**
     * Custom OnClickListener to be used with SuperActivityToasts/SuperCardToasts. Note that
     * SuperActivityToasts/SuperCardToasts must use this with an
     */
    public interface OnClickListener {

        void onClick(View view, Parcelable token);

    }

    /**
     * Custom OnDismissListener to be used with any type of SuperToasts. Note that
     * SuperActivityToasts/SuperCardToasts must use this with an
     */
    public interface OnDismissListener {

        void onDismiss(View view);

    }

    /**
     * Animations for all types of SuperToasts.
     */
    public enum Animations {

        FADE,
        FLYIN,
        SCALE,
        POPUP

    }

    /**
     * Icons for all types of SuperToasts.
     */
    public static class Icon {

        /**
         * Icons for all types of SuperToasts with a dark background.
         */
        public static class Dark {
            public static final int EXIT = (R.drawable.icon_dark_exit);
            public static final int INFO = (R.drawable.icon_dark_info);
        }
    }

    /**
     * Durations for all types of SuperToasts.
     */
    public static class Duration {

        public static final int VERY_SHORT = (1500);
        public static final int SHORT = (2000);
        public static final int MEDIUM = (2750);
        public static final int LONG = (3500);
        public static final int EXTRA_LONG = (4500);

    }

    /**
     * Text sizes for all types of SuperToasts.
     */
    public static class TextSize {

        public static final int EXTRA_SMALL = (12);
        public static final int SMALL = (14);
        public static final int MEDIUM = (16);
        public static final int LARGE = (18);

    }

    /**
     * Types for SuperActivityToasts and SuperCardToasts.
     */
    public enum Type {

        /**
         * Standard type used for displaying messages.
         */
        STANDARD,

        /**
         * Progress type used for showing progress.
         */
        PROGRESS,

        /**
         * Progress type used for showing progress.
         */
        PROGRESS_HORIZONTAL,

        /**
         * Button type used for receiving click actions.
         */
        BUTTON

    }

    /**
     * Positions for icons used in all types of SuperToasts.
     */
    public enum IconPosition {

        /**
         * Set the icon to the left of the text.
         */
        LEFT,

        /**
         * Set the icon to the right of the text.
         */
        RIGHT,

        /**
         * Set the icon on top of the text.
         */
        TOP,

        /**
         * Set the icon on the bottom of the text.
         */
        BOTTOM

    }

    private Animations mAnimations = Animations.FADE;
    private Context mContext;
    private int mGravity = Gravity.CENTER;
    private int mDuration = Duration.SHORT;
    private int mWindowWidth = 0,mWindowHeight=0;
    private int mTypefaceStyle;
    private Drawable mBackground;
    private int mXOffset = 0;
    private int mYOffset = 0;
    private LinearLayout mRootLayout;
    private OnDismissListener mOnDismissListener;
    private TextView mMessageTextView;
    private View mToastView;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowManagerParams;

    /**
     * Instantiates a new {@value #TAG}.
     *
     * @param context {@link Context}
     */
    public SuperToast(Context context) {

        if (context == null) {
            throw new IllegalArgumentException(TAG + ERROR_CONTEXTNULL);
        }

        this.mContext = context;
        mYOffset = context.getResources().getDimensionPixelSize(
                R.dimen.toast_hover);
        //mWindowHeight = mWindowWidth = (int) (context.getResources().getDisplayMetrics().widthPixels*2/5.0);
        mWindowHeight = mWindowWidth = WindowManager.LayoutParams.WRAP_CONTENT;
        final LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mToastView = layoutInflater.inflate(R.layout.supertoast, null);

        mWindowManager = (WindowManager) mToastView.getContext()
                .getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

        mRootLayout = (LinearLayout)
                mToastView.findViewById(R.id.root_layout);

        mMessageTextView = (TextView)
                mToastView.findViewById(R.id.message_textview);
        //this.setStyle(Style.getStyle(Style.BLACK));
    }

    /**
     * Instantiates a new {@value #TAG} with a specified style.
     *
     * @param context {@link Context}
     */
    public SuperToast(Context context, Style style) {

        if (context == null) {

            throw new IllegalArgumentException(TAG + ERROR_CONTEXTNULL);

        }

        this.mContext = context;
        mYOffset = context.getResources().getDimensionPixelSize(
                R.dimen.toast_hover);
        //mWindowHeight = mWindowWidth = (int) (context.getResources().getDisplayMetrics().widthPixels*2/5.0f);
        mWindowHeight = mWindowWidth = WindowManager.LayoutParams.WRAP_CONTENT;
        final LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mToastView = layoutInflater.inflate(R.layout.supertoast, null);

        mWindowManager = (WindowManager) mToastView.getContext()
                .getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

        mRootLayout = (LinearLayout)
                mToastView.findViewById(R.id.root_layout);

        mMessageTextView = (TextView)
                mToastView.findViewById(R.id.message_textview);

        this.setStyle(style);

    }

    /**
     * Shows the {@value #TAG}. If another {@value #TAG} is showing than
     * this one will be added to a queue and shown when the previous {@value #TAG}
     * is dismissed.
     */
    public void show() {

        mWindowManagerParams = new WindowManager.LayoutParams();

//        mWindowManagerParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        mWindowManagerParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowManagerParams.height = mWindowHeight;
        mWindowManagerParams.width = mWindowWidth;
        mWindowManagerParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        mWindowManagerParams.format = PixelFormat.TRANSLUCENT;
        mWindowManagerParams.windowAnimations = getAnimation();
        mWindowManagerParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        mWindowManagerParams.gravity = mGravity;
        mWindowManagerParams.x = mXOffset;
        mWindowManagerParams.y = mYOffset;

        ManagerSuperToast.getInstance().add(this);

    }

    /**
     * Sets the message text of the {@value #TAG}.
     *
     * @param text {@link CharSequence}
     */
    public void setText(CharSequence text) {

        mMessageTextView.setText(text);

    }

    /**
     * Returns the message text of the {@value #TAG}.
     *
     * @return {@link CharSequence}
     */
    public CharSequence getText() {

        return mMessageTextView.getText();

    }

    /**
     * Sets the message typeface style of the {@value #TAG}.
     *
     * @param typeface {@link Typeface} int
     */
    public void setTypefaceStyle(int typeface) {

        mTypefaceStyle = typeface;

        mMessageTextView.setTypeface(mMessageTextView.getTypeface(), typeface);

    }

    /**
     * Returns the message typeface style of the {@value #TAG}.
     *
     * @return {@link Typeface} int
     */
    public int getTypefaceStyle() {

        return mTypefaceStyle;

    }

    /**
     * Sets the message text color of the {@value #TAG}.
     *
     * @param textColor {@link Color}
     */
    public void setTextColor(int textColor) {

        mMessageTextView.setTextColor(textColor);

    }

    /**
     * Returns the message text color of the {@value #TAG}.
     *
     * @return int
     */
    public int getTextColor() {

        return mMessageTextView.getCurrentTextColor();

    }

    /**
     * Sets the text size of the {@value #TAG} message.
     *
     * @param textSize int
     */
    public void setTextSize(int textSize) {

        mMessageTextView.setTextSize(textSize);

    }

    /**
     * Returns the text size of the {@value #TAG} message in pixels.
     *
     * @return float
     */
    public float getTextSize() {

        return mMessageTextView.getTextSize();

    }

    /**
     * Sets the duration that the {@value #TAG} will show.
     *
     */
    public void setDuration(int duration) {

        if (duration > Duration.EXTRA_LONG) {

            Log.e(TAG, TAG + ERROR_DURATIONTOOLONG);

            this.mDuration = Duration.EXTRA_LONG;

        } else {

            this.mDuration = duration;

        }

    }

    /**
     * Returns the duration of the {@value #TAG}.
     *
     * @return int
     */
    public int getDuration() {

        return this.mDuration;

    }

    /**
     * Sets an icon resource to the {@value #TAG} with a specified position.
     *
     */
    public void setIcon(int iconResource, IconPosition iconPosition) {

        if (iconPosition == IconPosition.BOTTOM) {

            mMessageTextView.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    null, mContext.getResources().getDrawable(iconResource));

        } else if (iconPosition == IconPosition.LEFT) {

            mMessageTextView.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources()
                    .getDrawable(iconResource), null, null, null);

        } else if (iconPosition == IconPosition.RIGHT) {

            mMessageTextView.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    mContext.getResources().getDrawable(iconResource), null);

        } else if (iconPosition == IconPosition.TOP) {

            mMessageTextView.setCompoundDrawablesWithIntrinsicBounds(null,
                    mContext.getResources().getDrawable(iconResource), null, null);

        }

    }

    /**
     * Sets the background resource of the {@value #TAG}.
     *
     */
    public void setBackground(Drawable background) {

        this.mBackground = background;

        mRootLayout.setBackgroundDrawable(background);

    }

    /**
     * Returns the background resource of the {@value #TAG}.
     *
     * @return int
     */
    public Drawable getBackground() {

        return this.mBackground;

    }

    /**
     * Sets the gravity of the {@value #TAG} along with x and y offsets.
     *
     * @param gravity {@link Gravity} int
     * @param xOffset int
     * @param yOffset int
     */
    public void setGravity(int gravity, int xOffset, int yOffset) {

        this.mGravity = gravity;
        this.mXOffset = xOffset;
        this.mYOffset = yOffset;

    }

    /**
     * Sets the show/hide animations of the {@value #TAG}.
     *
     */
    public void setAnimations(Animations animations) {

        this.mAnimations = animations;

    }

    /**
     * Returns the show/hide animations of the {@value #TAG}.
     *
     */
    public Animations getAnimations() {

        return this.mAnimations;

    }

    /**
     * Sets an OnDismissListener defined in this library
     * to the {@value #TAG}. Does not require wrapper.
     *
     */
    public void setOnDismissListener(OnDismissListener onDismissListener) {

        this.mOnDismissListener = onDismissListener;

    }

    /**
     * Returns the OnDismissListener set to the {@value #TAG}.
     *
     */
    public OnDismissListener getOnDismissListener() {

        return mOnDismissListener;

    }

    /**
     * Dismisses the {@value #TAG}.
     */
    public void dismiss() {

        ManagerSuperToast.getInstance().removeSuperToast(this);

    }

    /**
     * Returns the {@value #TAG} message textview.
     *
     * @return {@link TextView}
     */
    public TextView getTextView() {

        return mMessageTextView;

    }

    /**
     * Returns the {@value #TAG} view.
     *
     * @return {@link View}
     */
    public View getView() {

        return mToastView;

    }

    /**
     * Returns true if the {@value #TAG} is showing.
     *
     * @return boolean
     */
    public boolean isShowing() {

        return mToastView != null && mToastView.isShown();

    }

    /**
     * Returns the window manager that the {@value #TAG} is attached to.
     *
     * @return {@link WindowManager}
     */
    public WindowManager getWindowManager() {

        return mWindowManager;

    }

    /**
     * Returns the window manager layout params of the {@value #TAG}.
     *
     * @return {@link WindowManager.LayoutParams}
     */
    public WindowManager.LayoutParams getWindowManagerParams() {

        return mWindowManagerParams;

    }

    /**
     * Private method used to return a specific animation for a animations enum
     */
    private int getAnimation() {

        if (mAnimations == Animations.FLYIN) {

            return android.R.style.Animation_Translucent;

        } else if (mAnimations == Animations.SCALE) {

            return android.R.style.Animation_Dialog;

        } else if (mAnimations == Animations.POPUP) {

            return android.R.style.Animation_InputMethod;

        } else {

            return android.R.style.Animation_Toast;

        }

    }

    /**
     * Private method used to set a default style to the {@value #TAG}
     */
    private void setStyle(Style style) {

        this.setAnimations(style.animations);
        this.setTypefaceStyle(style.typefaceStyle);
        this.setTextColor(style.textColor);
        this.setBackground(style.background);

    }

    /**
     * Returns a standard {@value #TAG}.
     *
     * @param context          {@link Context}
     * @param textCharSequence {@link CharSequence}
     *
     * @return {@link SuperToast}
     */
    public static SuperToast create(Context context, CharSequence textCharSequence,
                                    int durationInteger) {

        SuperToast superToast = new SuperToast(context);
        superToast.setText(textCharSequence);
        superToast.setDuration(durationInteger);

        return superToast;

    }

    /**
     * Returns a standard {@value #TAG} with specified animations.
     *
     * @param context          {@link Context}
     * @param textCharSequence {@link CharSequence}
     *
     * @return {@link SuperToast}
     */
    public static SuperToast create(Context context, CharSequence textCharSequence,
                                    int durationInteger, Animations animations) {

        final SuperToast superToast = new SuperToast(context);
        superToast.setText(textCharSequence);
        superToast.setDuration(durationInteger);
        superToast.setAnimations(animations);

        return superToast;

    }

    /**
     * Returns a {@value #TAG} with a specified style.
     *
     * @param context          {@link Context}
     * @param textCharSequence {@link CharSequence}
     *
     * @return SuperCardToast
     */
    public static SuperToast create(Context context, CharSequence textCharSequence, int durationInteger, Style style) {

        final SuperToast superToast = new SuperToast(context);
        superToast.setText(textCharSequence);
        superToast.setDuration(durationInteger);
        superToast.setStyle(style);

        return superToast;

    }

    /**
     * Dismisses and removes all showing/pending {@value #TAG}.
     */
    public static void cancelAllSuperToasts() {
        ManagerSuperToast.getInstance().cancelAllSuperToasts();
    }

    public static class ManagerSuperToast extends Handler {

        @SuppressWarnings("UnusedDeclaration")
        private static final String TAG = "ManagerSuperToast";

        /* Potential messages for the handler to send **/
        private static final class Messages {

            /* Hexadecimal numbers that represent acronyms for the operation **/
            private static final int DISPLAY_SUPERTOAST = 0x445354;
            private static final int ADD_SUPERTOAST = 0x415354;
            private static final int REMOVE_SUPERTOAST = 0x525354;

        }

        private static ManagerSuperToast mManagerSuperToast;

        private final Queue<SuperToast> mQueue;

        /* Private method to create a new list if the manager is being initialized */
        private ManagerSuperToast() {

            mQueue = new LinkedBlockingQueue<SuperToast>();

        }

        /* Singleton method to ensure all SuperToasts are passed through the same manager */
        protected static synchronized ManagerSuperToast getInstance() {

            if (mManagerSuperToast != null) {

                return mManagerSuperToast;

            } else {

                mManagerSuperToast = new ManagerSuperToast();

                return mManagerSuperToast;

            }

        }

        /* Add SuperToast to queue and try to show it */
        protected void add(SuperToast superToast) {
            cancelAllSuperToasts();//��ȡ����ǰ��
        /* Add SuperToast to queue and try to show it */
            mQueue.add(superToast);
            this.showNextSuperToast();

        }

        /* Shows the next SuperToast in the list */
        private void showNextSuperToast() {

            if (mQueue.isEmpty()) {

            /* There is no SuperToast to display next */

                return;

            }

        /* Get next SuperToast in the queue */
            final SuperToast superToast = mQueue.peek();

        /* Show SuperToast if none are showing (not sure why this works but it does) */
            if (!superToast.isShowing()) {

                final Message message = obtainMessage(Messages.ADD_SUPERTOAST);
                message.obj = superToast;
                sendMessage(message);

            } else {

                sendMessageDelayed(superToast, Messages.DISPLAY_SUPERTOAST,
                        getDuration(superToast));

            }

        }

        /* Show/dismiss a SuperToast after a specific duration */
        private void sendMessageDelayed(SuperToast superToast, final int messageId, final long delay) {

            Message message = obtainMessage(messageId);
            message.obj = superToast;
            sendMessageDelayed(message, delay);

        }

        /* Get duration and add one second to compensate for show/hide animations */
        private long getDuration(SuperToast superToast) {

            long duration = superToast.getDuration();
            duration += 1000;

            return duration;

        }

        @Override
        public void handleMessage(Message message) {

            final SuperToast superToast = (SuperToast)
                    message.obj;

            switch (message.what) {

                case Messages.DISPLAY_SUPERTOAST:

                    showNextSuperToast();

                    break;

                case Messages.ADD_SUPERTOAST:

                    displaySuperToast(superToast);

                    break;

                case Messages.REMOVE_SUPERTOAST:

                    removeSuperToast(superToast);

                    break;

                default: {

                    super.handleMessage(message);

                    break;

                }

            }

        }

        /* Displays a SuperToast */
        private void displaySuperToast(SuperToast superToast) {

            if (superToast.isShowing()) {

            /* If the SuperToast is already showing do not show again */

                return;

            }

            final WindowManager windowManager = superToast
                    .getWindowManager();

            final View toastView = superToast.getView();

            final WindowManager.LayoutParams params = superToast
                    .getWindowManagerParams();

            if (windowManager != null) {

                windowManager.addView(toastView, params);

            }

            sendMessageDelayed(superToast, Messages.REMOVE_SUPERTOAST,
                    superToast.getDuration() + 500);

        }

        /* Hide and remove the SuperToast */
        protected void removeSuperToast(SuperToast superToast) {

            final WindowManager windowManager = superToast
                    .getWindowManager();

            final View toastView = superToast.getView();

            if (windowManager != null) {

                mQueue.poll();

                windowManager.removeView(toastView);

                sendMessageDelayed(superToast,
                        Messages.DISPLAY_SUPERTOAST, 500);

                if (superToast.getOnDismissListener() != null) {

                    superToast.getOnDismissListener().onDismiss(superToast.getView());

                }

            }

        }

        /* Cancels/removes all showing pending SuperToasts */
        protected void cancelAllSuperToasts() {

            removeMessages(Messages.ADD_SUPERTOAST);
            removeMessages(Messages.DISPLAY_SUPERTOAST);
            removeMessages(Messages.REMOVE_SUPERTOAST);

            for (SuperToast superToast : mQueue) {

                if (superToast.isShowing()) {

                    superToast.getWindowManager().removeView(
                            superToast.getView());

                }

            }

            mQueue.clear();

        }

    }

    public static class Style {
        public static final int BLACK = 0;
        public static final int BLUE = 1;
        public static final int GRAY = 2;
        public static final int GREEN = 3;
        public static final int ORANGE = 4;
        public static final int PURPLE = 5;
        public static final int RED = 6;
        public static final int WHITE = 7;

        public Animations animations = Animations.FADE;
        public Drawable background = getBackground(GRAY);
        public int typefaceStyle = Typeface.NORMAL;
        public int textColor = Color.WHITE;
        public int dividerColor = Color.WHITE;
        public int buttonTextColor = Color.LTGRAY;

        /**
         * Returns a preset style.
         *
         * @param styleType {@link Style}
         *
         * @return {@link Style}
         */
        public static Style getStyle(int styleType) {

            final Style style = new Style();

            switch (styleType) {

                case BLACK:

                    style.textColor = Color.WHITE;
                    style.background = getBackground(BLACK);
                    style.dividerColor = Color.WHITE;
                    return style;

                case WHITE:

                    style.textColor = Color.DKGRAY;
                    style.background = getBackground(WHITE);
                    style.dividerColor = Color.DKGRAY;
                    style.buttonTextColor = Color.GRAY;
                    return style;

                case GRAY:

                    style.textColor = Color.WHITE;
                    style.background = getBackground(GRAY);
                    style.dividerColor = Color.WHITE;
                    style.buttonTextColor = Color.GRAY;
                    return style;

                case PURPLE:

                    style.textColor = Color.WHITE;
                    style.background = getBackground(PURPLE);
                    style.dividerColor = Color.WHITE;
                    return style;

                case RED:

                    style.textColor = Color.WHITE;
                    style.background = getBackground(RED);
                    style.dividerColor = Color.WHITE;
                    return style;

                case ORANGE:

                    style.textColor = Color.WHITE;
                    style.background = getBackground(ORANGE);
                    style.dividerColor = Color.WHITE;
                    return style;

                case BLUE:

                    style.textColor = Color.WHITE;
                    style.background = getBackground(BLUE);
                    style.dividerColor = Color.WHITE;
                    return style;

                case GREEN:

                    style.textColor = Color.WHITE;
                    style.background = getBackground(GREEN);
                    style.dividerColor = Color.WHITE;
                    return style;

                default:

                    style.textColor = Color.WHITE;
                    style.background = getBackground(GRAY);
                    style.dividerColor = Color.WHITE;
                    return style;

            }

        }

        /**
         * Returns a preset style with specified animations.
         *
         * @param styleType {@link Style}
         *
         * @return {@link Style}
         */
        public static Style getStyle(int styleType, Animations animations) {

            final Style style = new Style();
            style.animations = animations;

            switch (styleType) {

                case BLACK:
                    style.textColor = Color.WHITE;
                    style.background = getBackground(BLACK);
                    style.dividerColor = Color.WHITE;
                    return style;

                case WHITE:

                    style.textColor = Color.DKGRAY;
                    style.background = getBackground(WHITE);
                    style.dividerColor = Color.DKGRAY;
                    style.buttonTextColor = Color.GRAY;
                    return style;

                case GRAY:

                    style.textColor = Color.WHITE;
                    style.background = getBackground(GRAY);
                    style.dividerColor = Color.WHITE;
                    style.buttonTextColor = Color.GRAY;
                    return style;

                case PURPLE:

                    style.textColor = Color.WHITE;
                    style.background = getBackground(PURPLE);
                    style.dividerColor = Color.WHITE;
                    return style;

                case RED:

                    style.textColor = Color.WHITE;
                    style.background = getBackground(RED);
                    style.dividerColor = Color.WHITE;
                    return style;

                case ORANGE:

                    style.textColor = Color.WHITE;
                    style.background = getBackground(ORANGE);
                    style.dividerColor = Color.WHITE;
                    return style;

                case BLUE:
                    style.textColor = Color.WHITE;
                    style.background = getBackground(BLUE);
                    style.dividerColor = Color.WHITE;
                    return style;
                case GREEN:
                    style.textColor = Color.WHITE;
                    style.background = getBackground(GREEN);
                    style.dividerColor = Color.WHITE;
                    return style;
                default:
                    style.textColor = Color.WHITE;
                    style.background = getBackground(GRAY);
                    style.dividerColor = Color.WHITE;
                    return style;

            }

        }

        private static Drawable getBackground(int color) {
            GradientDrawable drawable = new GradientDrawable();
            drawable.setCornerRadius(10);
            switch (color) {
                case WHITE:
                    drawable.setColor(0xffffffff);
                    break;
                case BLACK:
                    drawable.setColor(0xff000000);
                    break;
            }
            return drawable;
        }
    }
}


