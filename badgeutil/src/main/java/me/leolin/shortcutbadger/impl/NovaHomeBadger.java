package me.leolin.shortcutbadger.impl;

import android.app.Notification;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import java.util.Arrays;
import java.util.List;

import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;

/**
 * Shortcut Badger support for Nova Launcher.
 * TeslaUnread must be installed.
 * User: Gernot Pansy
 * Date: 2014/11/03
 * Time: 7:15
 */
public class NovaHomeBadger extends Badger {
    private static final String CONTENT_URI = "content://com.teslacoilsw.notifier/unread_count";
    private static final String COUNT = "count";
    private static final String TAG = "tag";

    @Override
    public void executeBadge(Context context, ComponentName componentName, Notification notification, int notificationId, int thisNotificationCount, int badgeCount) throws ShortcutBadgeException {
        setNotification(notification, notificationId, context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(TAG, componentName.getPackageName() + "/" + componentName.getClassName());
        contentValues.put(COUNT, badgeCount);
        context.getContentResolver().insert(Uri.parse(CONTENT_URI), contentValues);
    }

    @Override
    public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TAG, componentName.getPackageName() + "/" + componentName.getClassName());
        contentValues.put(COUNT, badgeCount);
        context.getContentResolver().insert(Uri.parse(CONTENT_URI), contentValues);
    }

    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.teslacoilsw.launcher");
    }

}