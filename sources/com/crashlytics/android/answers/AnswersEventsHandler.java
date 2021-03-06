package com.crashlytics.android.answers;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.Kit;
import p041io.fabric.sdk.android.services.events.EventsStorageListener;
import p041io.fabric.sdk.android.services.network.HttpRequestFactory;
import p041io.fabric.sdk.android.services.settings.AnalyticsSettingsData;

class AnswersEventsHandler implements EventsStorageListener {
    private final Context context;
    final ScheduledExecutorService executor;
    private final AnswersFilesManagerProvider filesManagerProvider;
    private final FirebaseAnalyticsApiAdapter firebaseAnalyticsApiAdapter;
    private final Kit kit;
    private final SessionMetadataCollector metadataCollector;
    private final HttpRequestFactory requestFactory;
    SessionAnalyticsManagerStrategy strategy = new DisabledSessionAnalyticsManagerStrategy();

    /* renamed from: com.crashlytics.android.answers.AnswersEventsHandler$2 */
    class C16192 implements Runnable {
        C16192() {
        }

        public void run() {
            try {
                SessionAnalyticsManagerStrategy prevStrategy = AnswersEventsHandler.this.strategy;
                AnswersEventsHandler.this.strategy = new DisabledSessionAnalyticsManagerStrategy();
                prevStrategy.deleteAllEvents();
            } catch (Exception e) {
                Fabric.getLogger().mo34406e("Answers", "Failed to disable events", e);
            }
        }
    }

    /* renamed from: com.crashlytics.android.answers.AnswersEventsHandler$3 */
    class C16203 implements Runnable {
        C16203() {
        }

        public void run() {
            try {
                AnswersEventsHandler.this.strategy.sendEvents();
            } catch (Exception e) {
                Fabric.getLogger().mo34406e("Answers", "Failed to send events files", e);
            }
        }
    }

    /* renamed from: com.crashlytics.android.answers.AnswersEventsHandler$4 */
    class C16214 implements Runnable {
        C16214() {
        }

        public void run() {
            try {
                SessionEventMetadata metadata = AnswersEventsHandler.this.metadataCollector.getMetadata();
                SessionAnalyticsFilesManager filesManager = AnswersEventsHandler.this.filesManagerProvider.getAnalyticsFilesManager();
                filesManager.registerRollOverListener(AnswersEventsHandler.this);
                AnswersEventsHandler.this.strategy = new EnabledSessionAnalyticsManagerStrategy(AnswersEventsHandler.this.kit, AnswersEventsHandler.this.context, AnswersEventsHandler.this.executor, filesManager, AnswersEventsHandler.this.requestFactory, metadata, AnswersEventsHandler.this.firebaseAnalyticsApiAdapter);
            } catch (Exception e) {
                Fabric.getLogger().mo34406e("Answers", "Failed to enable events", e);
            }
        }
    }

    /* renamed from: com.crashlytics.android.answers.AnswersEventsHandler$5 */
    class C16225 implements Runnable {
        C16225() {
        }

        public void run() {
            try {
                AnswersEventsHandler.this.strategy.rollFileOver();
            } catch (Exception e) {
                Fabric.getLogger().mo34406e("Answers", "Failed to flush events", e);
            }
        }
    }

    public AnswersEventsHandler(Kit kit, Context context, AnswersFilesManagerProvider filesManagerProvider, SessionMetadataCollector metadataCollector, HttpRequestFactory requestFactory, ScheduledExecutorService executor, FirebaseAnalyticsApiAdapter firebaseAnalyticsApiAdapter) {
        this.kit = kit;
        this.context = context;
        this.filesManagerProvider = filesManagerProvider;
        this.metadataCollector = metadataCollector;
        this.requestFactory = requestFactory;
        this.executor = executor;
        this.firebaseAnalyticsApiAdapter = firebaseAnalyticsApiAdapter;
    }

    public void processEventAsync(Builder eventBuilder) {
        processEvent(eventBuilder, false, false);
    }

    public void processEventAsyncAndFlush(Builder eventBuilder) {
        processEvent(eventBuilder, false, true);
    }

    public void processEventSync(Builder eventBuilder) {
        processEvent(eventBuilder, true, false);
    }

    public void setAnalyticsSettingsData(final AnalyticsSettingsData analyticsSettingsData, final String protocolAndHostOverride) {
        executeAsync(new Runnable() {
            public void run() {
                try {
                    AnswersEventsHandler.this.strategy.setAnalyticsSettingsData(analyticsSettingsData, protocolAndHostOverride);
                } catch (Exception e) {
                    Fabric.getLogger().mo34406e("Answers", "Failed to set analytics settings data", e);
                }
            }
        });
    }

    public void disable() {
        executeAsync(new C16192());
    }

    public void onRollOver(String rolledOverFile) {
        executeAsync(new C16203());
    }

    public void enable() {
        executeAsync(new C16214());
    }

    public void flushEvents() {
        executeAsync(new C16225());
    }

    /* Access modifiers changed, original: 0000 */
    public void processEvent(final Builder eventBuilder, boolean sync, final boolean flush) {
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    AnswersEventsHandler.this.strategy.processEvent(eventBuilder);
                    if (flush) {
                        AnswersEventsHandler.this.strategy.rollFileOver();
                    }
                } catch (Exception e) {
                    Fabric.getLogger().mo34406e("Answers", "Failed to process event", e);
                }
            }
        };
        if (sync) {
            executeSync(runnable);
        } else {
            executeAsync(runnable);
        }
    }

    private void executeSync(Runnable runnable) {
        try {
            this.executor.submit(runnable).get();
        } catch (Exception e) {
            Fabric.getLogger().mo34406e("Answers", "Failed to run events task", e);
        }
    }

    private void executeAsync(Runnable runnable) {
        try {
            this.executor.submit(runnable);
        } catch (Exception e) {
            Fabric.getLogger().mo34406e("Answers", "Failed to submit events task", e);
        }
    }
}
