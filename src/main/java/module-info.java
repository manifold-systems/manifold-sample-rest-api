/**
 * Note, this module-info.java file is not necessary; it is only here to demonstrate setting one up.
 * Feel free to delete it if you are not interested in named modules.
 */
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