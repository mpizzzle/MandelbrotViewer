#!/usr/bin/env sh

rm -rf build/
mkdir build
cd build/ && cmake ..
cd .. && cmake --build build/
rm MandelbrotViewer
cp build/MandelbrotViewer ./
