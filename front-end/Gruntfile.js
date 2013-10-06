module.exports = function(grunt) {

  // configure the tasks
  grunt.initConfig({

    clean: {
      build: {
        src: ['build']
      },
      stylesheets: {
        src: ['build/**/*.css', '!build/css/application.css']
      },
      scripts: {
        src: ['build/**/*.js', '!build/js/application.js']
      },
    },

    copy: {
      build: {
        cwd: 'src',
        src: ['**'],
        dest: 'build',
        expand: true
      },
    },

    cssmin: {
      build: {
        files: {
          'build/css/application.css': ['build/**/*.css']
        }
      },
    },

    jshint: {
      files: ['src/**/*.js'],
    },

    uglify: {
      build: {
        options: {
          mangle: false
        },
        files: {
          'build/js/application.js': ['build/**/*.js']
        }
      }
    },

    watch: {
      stylesheets: {
        files: 'src/**/*.css',
        tasks: ['stylesheets']
      },
      scripts: {
        files: 'src/**/*.js',
        tasks: ['scripts']
      },
      copy: {
        files: ['src/**/*', '!src/**/*.css', '!src/**/*.js'],
        tasks: ['copy']
      }
    },

    connect: {
      server: {
        options: {
          port: 4000,
          base: 'build',
          hostname: '*'
        }
      }
    }

  });

  // load the tasks
  grunt.loadNpmTasks('grunt-contrib-clean');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-contrib-jshint');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-connect');

  // define the tasks
  grunt.registerTask(
    'build',
    'Compiles all of the assets and copies the files to the build directory.', ['clean:build', 'copy', 'scripts', 'stylesheets']
  );

  grunt.registerTask(
    'stylesheets',
    'Compiles the stylesheets.', ['cssmin', 'clean:stylesheets']
  );

  grunt.registerTask(
    'scripts',
    'Compiles the JavaScript files.', ['jshint', 'uglify', 'clean:scripts']
  );

  grunt.registerTask(
    'default',
    'Watches the project for changes, automatically builds them and runs a server.', ['build', 'connect', 'watch']
  );
};