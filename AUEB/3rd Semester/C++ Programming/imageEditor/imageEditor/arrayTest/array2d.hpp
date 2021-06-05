#pragma once
#include <iostream>
using namespace std;
#include "array2d.h"
#include <vector>



namespace math {
	template<typename T>
	const unsigned int Array2D<T>::getWidth() const {
		return width;
	}


	template<typename T>
	const unsigned int Array2D<T>::getHeight() const {
		return height;
	}


	template<typename T>
	T* Array2D<T>::getRawDataPtr() {
		T* pointer = this->buffer.data();
		return pointer;
	}

	template<typename T> // me initializer list.
	Array2D<T>::Array2D(unsigned int width, unsigned int height, const T* data_ptr): width(width),height(height),buffer(data_ptr,data_ptr + width * height){
		cout << "Array2D Constructor called." << endl;
	}

	template<typename T>
	void  Array2D<T>::setData(const T* const& data_ptr) {
		if (getWidth() == 0 || getHeight() == 0 || getRawDataPtr() == 0)
			return;
		for (unsigned int i = 0; i < width * height; i++)
			this->buffer[i] = data_ptr[i];
	}

	template<typename T>
	T& Array2D<T>::operator () (unsigned int x, unsigned int y) {
		return this->buffer[x * width + y];
	}

	template<typename T>
	Array2D<T>::Array2D(const Array2D& src) : width(src.width), height(src.height), buffer(src.buffer) { // Copy Constructor.
		cout << "Copy Constructor called." << endl;
	}



	template<typename T>
	Array2D<T>::~Array2D(){   // destructor.
		vector<T>().swap(buffer); // kanoume swap ton buffer me ena adeio vector kai etsi apodesmeyetai h mnhmh swsta.(katastrefetai kai to item kai ta periexomena tou vector.)
		cout << "Array2D Destructor called." << endl;
	} 

	template<typename T>
	Array2D<T>& Array2D<T>::operator = (const Array2D& right) {
		this->width = right.width;
		this->height = right.height;
		this->buffer = right.buffer;
		cout << "Copy assignment operator called." << endl;
		return *this;
	} 
}