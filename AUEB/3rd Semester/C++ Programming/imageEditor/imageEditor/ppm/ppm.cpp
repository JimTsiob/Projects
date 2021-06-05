// ppm.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <fstream>
using namespace std;

namespace image {
	float* ReadPPM(const char* filename, int* w, int* h) {
		string pSix; // metavlhth gia elegxo tou magic number (P6).
		string bytes; // metavlhth gia elegxo toy max arithmou bytes.
		char* space = new char[1]; // tha parei to keno metaxy 255 kai data.
		std::ifstream file; // input file stream gia na mporesoume na diavasoume to arxeio.
		file.open(filename,ios::binary); // open se binary form.
		if (!file.is_open()) { //elegxos gia to read.
			cout << "Reading failed." << endl;
			return nullptr; // an den petyxei to read, return nullptr.
		}
		else {
			cout << "PPM file read." << endl;
		}
		file >> pSix;
		if (pSix != "P6") {
			cout << "It's not a PPM File.";
			return nullptr;
		}
		file >> *w; // read width.
		file >> *h; // read height.
		file >> bytes;
		if (bytes > "255" || bytes < "0") {
			cout << "Invalid input." << endl;
			return nullptr;
		}
		int size = 3 * (*w) * (*h);
		file.read(space, 1);
		float* pixels = new float[size]; // o pinakas pou tha apothikeysei ta data kai tha epistrafei.
		unsigned char* convert = new unsigned char[size]; // pinakas pou kanei ta stoixeia na einai [0,1].
		file.read((char*)convert, size);
		for (int i = 0; i < size; i++) {
			pixels[i] = (float)convert[i]/255.0f;
		}
		file.close(); //close file.
		delete[] convert;
		delete[] space;
		return pixels; //return ton float* pinaka mas.
	}


	bool WritePPM(const float* data, int w, int h, const char* filename) {
		std::ofstream file; // to output file mas.
		file.open(filename, ios::out | ios::binary); // open se binary form.
		if (!file.is_open()) {
			cout << "Writing Failed." << endl;
			return false; // an apotyxei to write epistrefei false.
		}
		file << "P6" << endl; // write magic number.
		file << w << endl; // write width.
		file << h << endl; // write height.
		file << "255" << endl; // write max bytes.
		int size = 3 * w * h;
		unsigned char* convert = new unsigned char[size]; // pinakas gia metatroph apo [0,1] se binary form.
		for (int i = 0; i < size; i++) {
			convert[i] = data[i] * 255.0f; // metatroph se binary form.
		}
		cout << (int)convert[0] << endl;
		for (int i = 0; i < size; i++) {
			file << convert[i];
		}
		file.close();
		cout << "File written." << endl;
		delete[] convert;
		return true;

	}
}




