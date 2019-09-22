cmake_minimum_required(VERSION 3.0)
project(MandelbrotViewer)
set(CMAKE_CXX_STANDARD 11)

## DEPENDENCIES

find_package(SFML 2.5 COMPONENTS system window graphics REQUIRED)

## TARGETS

if(WIN32)
  add_executable(MandelbrotViewer WIN32 src/Mandelbrot.cpp)
else()
  add_executable(MandelbrotViewer src/Mandelbrot.cpp)
endif()
target_compile_definitions(MandelbrotViewer PRIVATE SFML_STATIC)
target_link_libraries(MandelbrotViewer
  sfml-system
  sfml-window
  sfml-graphics
)
