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
-keep public interface com.poilabs.poilabspositioning.callback.ChangeEventListener
-keep public interface com.poilabs.poilabspositioning.callback.BeaconPositionFinderCallback
-keep public interface com.poilabs.poilabspositioning.callback.BeaconScanCallback
-keep public interface com.poilabs.poilabspositioning.callback.PositioningCallback



-keep class com.poilabs.poilabspositioning.model.** { *; }
-keep class com.poilabs.poilabspositioning.PoilabsPositioning { *; }

-keep public interface com.poilabs.navigation.model.NavCallbacks$ChildListener
-keep public interface com.poilabs.navigation.model.PositionCallback
-keep public interface com.poilabs.navigation.model.PoiNavigation$OnNavigationReady
-keep public interface poilabs.com.poinavigationsdk.ui.customAlertDialog.iOSDialogClickListener
-keep public interface com.poilabs.navigation.model.DataManager$ApiInterface



-keep class poilabs.com.poinavigationsdk.model.** { *; }
-keep class com.poilabs.navigation.model.** { *; }
-keep class com.poilabs.android.poilabsanalytics.model.** { *; }
-keep class com.poilabs.navigation.model.PoiNavigation { *; }
-keep class com.poilabs.navigation.model.PoiSdkConfig { *; }

-dontwarn android.databinding.**
-keep class android.databinding.** { *; }

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}
