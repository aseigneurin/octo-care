var srcDir = 'front-end/src';
var buildDir = 'back-end/src/main/webapp';

module.exports = function(grunt) {

  // configure the tasks
  grunt.initConfig({

    clean: {
      build: {
        src: [buildDir + '/*.html', buildDir + '/css', buildDir + '/js', buildDir + '/partials']
      },
      stylesheets: {
        src: [buildDir + '/**/*.css', '!' + buildDir + '/css/application.css']
      },
      scripts: {
        src: [buildDir + '/**/*.js', '!' + buildDir + '/js/application.js']
      },
    },

    copy: {
      build: {
        cwd: srcDir,
        src: ['**'],
        dest: buildDir,
        expand: true
      },
    },

    cssmin: {
      build: {
        files: {
          'back-end/src/main/webapp/css/application.css': [buildDir + '/**/*.css']
        }
      },
    },

    jshint: {
      files: [srcDir + '/**/*.js'],
    },

    uglify: {
      build: {
        options: {
          mangle: false
        },
        files: {
          'back-end/src/main/webapp/js/application.js': [buildDir + '/**/*.js']
        }
      }
    },

    watch: {
      stylesheets: {
        files: srcDir + '/**/*.css',
        tasks: ['stylesheets']
      },
      scripts: {
        files: srcDir + '/**/*.js',
        tasks: ['scripts']
      },
      copy: {
        files: [srcDir + '/**/*', '!src/**/*.css', '!src/**/*.js'],
        tasks: ['copy']
      }
    },

    connect: {
      server: {
        options: {
          port: 4000,
          base: buildDir,
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