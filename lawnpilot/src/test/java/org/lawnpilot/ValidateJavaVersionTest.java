package org.lawnpilot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.BuildTask;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;


public class ValidateJavaVersionTest {

    @TempDir
    File testProjectDir;

    @BeforeEach
    void setupProject() throws IOException {
        File buildFile = new File(testProjectDir, "build.gradle");
        try (FileWriter writer = new FileWriter(buildFile)) {
            writer.write(
                    """
                            plugins {
                                id 'java'
                            }
                            def fakeJavaVersion = providers.gradleProperty('fakeJavaVersion')
                            
                            tasks.register('validateJavaVersion') {
                                doFirst {
                                    def currentVersion = fakeJavaVersion.present
                                            ? JavaVersion.toVersion(fakeJavaVersion.get())
                                            : JavaVersion.current()
                                    if (!currentVersion.isCompatibleWith(JavaVersion.VERSION_17)) {
                                        throw new GradleException(
                                            "Build failed: You are using Java ${currentVersion}, this project requires Java 17 or higher."
                                        )
                                    }
                                }
                            }
                            """
            );
        }
    }

    @Test
    void testBuildFailsWhenJavaVersionIsTooLow() {
        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir)
                .withArguments("validateJavaVersion", "-PfakeJavaVersion=1.8")
                .buildAndFail(); //confirms build crashes as expected

        assertTrue(result.getOutput().contains("this project requires Java 17 or higher"));
    }

    @Test
    void testBuildSucceedsWhenJavaVersionIsSeventeen() {
        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir)
                .withArguments("validateJavaVersion", "-PfakeJavaVersion=17")
                .build();

        BuildTask taskResult = result.task(":validateJavaVersion");
        assertNotNull(taskResult);
        assertEquals(TaskOutcome.SUCCESS, taskResult.getOutcome());
    }

    @Test
    void testBuildSucceedsWhenJavaVersionIsHigherThanSeventeen() {
        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir)
                .withArguments("validateJavaVersion", "-PfakeJavaVersion=21")
                .build();

        BuildTask taskResult = result.task(":validateJavaVersion");
        assertNotNull(taskResult);
        assertEquals(TaskOutcome.SUCCESS, taskResult.getOutcome());
    }

}
