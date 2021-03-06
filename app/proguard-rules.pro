# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/okt/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-printmapping out.map
-keepparameternames
-renamesourcefileattribute SourceFile
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,EnclosingMethod

# Preserve all annotations.

-keepattributes *Annotation*

# Preserve all public classes, and their public and protected fields and
# methods.

-keep public class * {
    public protected *;
}

# Preserve all .class method names.

-keepclassmembernames class * {
    java.lang.Class class$(java.lang.String);
    java.lang.Class class$(java.lang.String, boolean);
}

# Preserve all native method names and the names of their classes.

-keepclasseswithmembernames class * {
    native <methods>;
}

# Preserve the special static methods that are required in all enumeration
# classes.

-keepclassmembers class * extends java.lang.Enum {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
# You can comment this out if your library doesn't use serialization.
# If your code contains serializable classes that have to be backward
# compatible, please refer to the manual.

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep public interface com.poilabs.navigation.model.NavCallbacks$ChildListener
-keep public interface com.poilabs.navigation.model.PoiNavigation$OnNavigationReady
-keep public interface com.poilabs.navigation.model.beaconutil.BeaconSenderHelper$PoiListener
-keep public interface poilabs.com.poinavigationsdk.ui.customAlertDialog.iOSDialogClickListener
-keep public interface com.poilabs.navigation.model.DataManager$ApiInterface



-keep class poilabs.com.poinavigationsdk.model.** { *; }
-keep class com.poilabs.navigation.model.** { *; }
-keep class com.poilabs.navigation.model.PoiNavigation { *; }
-keep class com.poilabs.navigation.model.PoiSdkConfig { *; }

-dontwarn android.databinding.**
-keep class android.databinding.** { *; }