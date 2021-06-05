#pragma once
#include <iostream>
#include "Image.h"
#include <cmath> // gia thn ypswsh dynamhs.

using namespace std;


class Filter {

	public:

		Filter() {
			cout << "Filter Constructor called." << endl;
		}

		// o pure virtual operator.
		virtual Image operator << (const Image& image) = 0;
};


class FilterLinear : public Filter {

	protected:

		Color a;
		Color c;

	public:

		FilterLinear(Color alpha = 0, Color charlie = 0 ) {
			a = alpha;
			c = charlie;
			cout << "FilterLinear Constuctor called." << endl;
		}

		FilterLinear(const FilterLinear& original) : a(original.a), c(original.c) {
			cout << "FilterLinear Copy Constructor called." << endl;
		}

		~FilterLinear() {
			cout << "FilterLinear Destructor called." << endl;
		}

		Image operator << (const Image& image) {
			cout << "FilterLinear operator called." << endl;
			Image item = Image(image);
			Color vec;
			int size = image.buffer.size();
			cout << "buffer size: " << size << endl;
			for (int i = 0; i < size; i++) {
				vec = image.buffer[i];
				vec = vec * a;
				vec = vec + c;
				if (vec.x < 0) {
					vec.x = 0.0f;
				}
				if (vec.y < 0) {
					vec.y = 0.0f;
				}
				if (vec.z < 0) {
					vec.z = 0.0f;
				}
				if (vec.x > 1) {
					vec.x = 1.0f;
				}
				if (vec.y > 1) {
					vec.y = 1.0f;
				}
				if (vec.z > 1) {
					vec.z = 1.0f;
				}
				item.buffer[i] = vec;
			}
			return item;
		}
};

class FilterGamma : public Filter {
	
	protected:

		float g;

	public:
		
		FilterGamma(float gamma) {
			g = gamma;
			cout << "FilterGamma constructor called." << endl;
		}

		FilterGamma(const FilterGamma& original) : g(original.g) {
			cout << "FilterGamma Copy Constructor called." << endl;
		}

		~FilterGamma() {
			cout << "FilterGamma Destructor called." << endl;
		}

		Image operator << (const Image& image) {
			Image pix = Image(image);
			int size = image.buffer.size();
			Color vec = Color();
			for (int i = 0; i < size; i++) {
				vec = image.buffer[i];
				vec.x = pow(vec.x, g);
				vec.y = pow(vec.y, g);
				vec.z = pow(vec.z, g);
				if (vec.x < 0) {
					vec.x = 0.0f;
				}
				if (vec.y < 0) {
					vec.y = 0.0f;
				}
				if (vec.z < 0) {
					vec.z = 0.0f;
				}
				if (vec.x > 1) {
					vec.x = 1.0f;
				}
				if (vec.y > 1) {
					vec.y = 1.0f;
				}
				if (vec.z > 1) {
					vec.z = 1.0f;
				}
				pix.buffer[i] = vec;
			}
			return pix;
		}
};