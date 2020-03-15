#!/bin/sh

set -x

env

echo "OS info"
cat /etc/os-release

echo "Installing google chrome"
wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | sudo apt-key add -
echo "deb http://dl.google.com/linux/chrome/deb/ stable main" | sudo tee /etc/apt/sources.list.d/google-chrome.list
sudo apt update
sudo apt install -y google-chrome-stable

echo "Installing npm"
sudo apt-get install curl
curl -sL https://deb.nodesource.com/setup_10.x | sudo -E bash -
sudo apt install -y build-essential nodejs

echo "npm version"
npm --version

echo "Install package of the project"
npm i
sudo npm i -g shadow-cljs karma-cli
shadow-cljs compile ci
