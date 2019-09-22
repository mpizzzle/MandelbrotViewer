#!/usr/bin/env sh

cd ~/MandelbrotViewer/
rm -rf build/
mkdir build
cd build/ && cmake ..
cd .. && cmake --build build/
rm MandelbrotViewer
cp build/MandelbrotViewer ./
