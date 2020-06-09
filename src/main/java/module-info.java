module manifold.sample.rest.api {
    // Use the JSON manifold for type-safe access to JSON schema and REST API
    requires manifold.json.rt;
    // Use SparkJava for lightweight web framework
    requires spark.core;

    // Include transitive dependencies manually since manifold jars are "automatic" modules
    // (they don't define manifold-info.java files, thus no 'requires' to their dependencies)
    requires manifold.rt;
    requires manifold.ext.rt;
    requires jdk.unsupported;
}