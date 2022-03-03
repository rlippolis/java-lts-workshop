package com.jdriven.jdkworkshop.demo;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;

import org.junit.jupiter.api.Test;

public class FlowApiTest {

    private static final Random RANDOM = new Random();

    @Test
    public void reactiveProgrammingWithFlowAPI() {
        final SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        subscribeToPublisher(publisher);

        publishNumbersAsynchronously(publisher);

        ForkJoinPool.commonPool().awaitQuiescence(10, SECONDS);
    }

    private void subscribeToPublisher(final Publisher<Integer> publisher) {
        publisher.subscribe(new Subscriber<>() {
            private Subscription subscription;

            @Override
            public void onSubscribe(final Subscription subscription) {
                System.out.println("onSubscribe");
                this.subscription = subscription;
                subscription.request(1);
            }

            @Override
            public void onNext(final Integer item) {
                System.out.println("onNext: " + item);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscription.request(1);
            }

            @Override
            public void onError(final Throwable throwable) {
                System.out.println("onError");
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    private void publishNumbersAsynchronously(final SubmissionPublisher<Integer> publisher) {
        final Executor executor = ForkJoinPool.commonPool();

        executor.execute(() -> {
            for (int i = 0; i < 10; i++) {
                if (!publisher.isClosed()) {
                    publisher.submit(RANDOM.nextInt(1000));
                }
            }
            publisher.close();
        });
    }
}
