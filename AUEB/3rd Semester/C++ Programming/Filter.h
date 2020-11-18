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
			cout << "width : " << item.width << endl;
			Color vec;
			for (int i=0; i<buffer.size(); i++){
				vec = image.buffer[i];
				vec = a*vec+c;
				item.buffer[i] = vec;
			}
			/*for (unsigned int i = 0; i < item.width; i++) {
				for (unsigned int j = 0; j < item.height; j++) {
					item(i, j).x = a.x * item(i, j).x + c.x;
					item(i, j).y = a.y * item(i, j).y + c.y;
					item(i, j).z = a.z * item(i, j).z + c.z;
					item(i, j).clampToLowerBound(0);
					item(i, j).clampToUpperBound(1);
				}
			}*/
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
			Color vec;
			for (int i = 0; i<image.buffer.size(); i++){
				vec = image.buffer[i];
				vec.x = pow(vec.x,g);
				vec.y = pow(vec.y,g);
				vec.z = pow(vec.z,g);
				item.buffer[i] = vec;
			}
			/*for (unsigned int i = 0; i < pix.width; i++) {
				for (unsigned int j = 0; j < pix.height; j++) {
					pix(i, j).x = pow(pix(i, j).x, g);
					pix(i, j).y = pow(pix(i, j).y, g);
					pix(i, j).z = pow(pix(i, j).z, g);
					pix(i, j).clampToLowerBound(0);
					pix(i, j).clampToUpperBound(1);
				}
			}*/
			return pix;
		}
};

class FilterBlur : public Filter, public math::Array2D<float> {
	protected:
		int N;
	public:
		Array2D::buffer;
		

		FilterBlur(int number) {
			cout << "blur constructor called." << endl;
			N = number;
		}

		FilterBlur(const FilterBlur& src) : N(src.N) {
			cout << "blur copy constructor called." << endl;
		}

		~FilterBlur() {
			vector<float>().swap(buffer);
		}

		Image operator << (const Image& image) {
			int squareN = pow(N, 2);
			for (int i = 0; i < buffer.size(); i++) {
				buffer[i] = 1 / squareN;
			}

			cout << buffer[0];
		}



};