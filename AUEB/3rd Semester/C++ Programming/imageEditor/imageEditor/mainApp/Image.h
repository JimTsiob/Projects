#pragma once
#include <iostream>
#include "array2d.h"
#include "imageio.h"
#include "vec3.h"
#include <vector>
#include "ppm.h"


using Color = math::Vec3<float>; // pseydwnymo.
using namespace std;



// declaration kai definition tou class Image kai twn methodwn ths.

class Image : public math::Array2D<Color>, image::ImageIO {
	public:
		Array2D::buffer;
		int width; // width , wste na mpei kai sto write ths save.
		int height; // height , wste na mpei kai sto write ths save.

		// Constructor.
		Image() {
			this->width = 0;
			this->height = 0;
			cout << "Image Constructor called." << endl;
		}

		// Copy constructor.
		Image(const Image& src) {
			cout << "Image Copy Constructor called." << endl;
			this->width = src.width;
			this->height = src.height;
			this->buffer = src.buffer;
		}
			

		// Destructor.
		~Image() {
			cout << "Image Destructor called." << endl;
			vector<Color>().swap(buffer); // pali topothetoume ton buffer se adeio vector gia apodesmeysh mnhmhs.
		} 

		

		// Definition of load.
		bool ImageIO::load(const string& filename, const string& format) {
			size_t pos = filename.find_last_of("."); // vriskei thn teleia.
			string ext = "";
			if (pos == string::npos){
				cout << "couldn't find a dot. Rewrite the file properly." << endl;
				return false;
			}else{
				ext = filename.substr(pos+1); // file extension.
			}
			// case insensitive elegxos apo katw.
			if (ext == "ppm" || ext == "Ppm" || ext == "pPm" || ext == "ppM" || ext == "PpM" || ext == "pPM" || ext == "PPm" || ext == "PPM" || ext == format){
				int newW = 0;
				int newH = 0; // oi times twn dyo metavlhtwn tha xrhsimopoihthoun sta width kai height ths klashs gia na ginetai swsth antigrafh sta filtra.
				const char* name = filename.c_str();
				float* data = image::ReadPPM(name,&newW,&newH);
				this->width = newW;
				this->height = newH;
				int length = 3 * newW * newH;
				cout << "length is " << length << endl;
				for (int i = 0; i < length; i = i + 3) { // edw diavazw ta stoixeia tou data ws XYZ, ta topothetw se ena Vec3 kai vazw to Vec3 ston buffer.
					Color vec = Color(data[i], data[i + 1], data[i + 2]);
					this->buffer.push_back(vec);
				}
				cout << buffer[0].x << " " << buffer[0].y << " " << buffer[0].z << endl;
				cout << "\nFile loaded succesfully." << endl;
				delete[] data; // apodesmeysh mnhmhs.
				return true;
			}
			else{
				cout << "Not a ppm file. Give the proper format." << endl;
				return false;
			}
		}


		// Definition of save.
		bool ImageIO::save(const string& filename, const string& format) {
			size_t pos = filename.find_last_of("."); // vriskei thn teleia.
			string ext = "";
			if (pos == string::npos) {
				cout << "couldn't find a dot. Rewrite the file properly." << endl;
				return false;
			}
			else {
				ext = filename.substr(pos + 1); // file extension.
			}
			// case insensitive elegxos apo katw.
			if (ext == "ppm" || ext == "Ppm" || ext == "pPm" || ext == "ppM" || ext == "PpM" || ext == "pPM" || ext == "PPm" || ext == "PPM" || ext == format) {
				const char* path = filename.c_str();
				Color vec = Color();
				int size = 3 * buffer.size();
				size = size * 3; // to kanoume pali *3 wste na diavazei swsta ola ta stoixeia.
				float* pinakas = new float[size];
				int c = 0; // dhmiourgw enan allo metrhth wste na mporw na topothetw ta stoixeia tou idiou vector se diaforetika kelia.
				for (int i = 0; i < buffer.size(); i++) {
					vec = buffer[i]; // fortwnoume arxika edw.
					pinakas[c] = vec.x;
					c++;
					pinakas[c] = vec.y;
					c++;
					pinakas[c] = vec.z;
					c++;	
				}
				cout << "Pinakas elements:  " << pinakas[0] << " " << pinakas[1] << " " << pinakas[2] << endl;
				image::WritePPM(pinakas, this->width, this->height, path); // pairnei ta width kai height ths klashs
				cout << "File saved successfully." << endl;
				delete[] pinakas;
				return true;
			}
			else {
				cout << "Saving failed." << endl;
				return false;
			}
			
		}

		


};