FROM php:7.4-fpm

# Copy composer.lock and composer.json
#COPY code/composer.lock* code/composer.json* /var/www/

# Set working directory
WORKDIR /var/www/ucreativa-api/public

# Install system dependencies
RUN apt-get update && apt-get install -y \
    git \
    curl \
    libpng-dev \
    libonig-dev \
    libxml2-dev \
    zip \
    unzip

# Clear cache
RUN apt-get clean && rm -rf /var/lib/apt/lists/*

# Install PHP extensions
RUN docker-php-ext-install pdo_mysql mbstring exif pcntl bcmath gd

# Get latest Composer
COPY --from=composer:latest /usr/bin/composer /usr/bin/composer

# Add user for laravel application
RUN groupadd -g 1000 www
RUN useradd -u 1000 -ms /bin/bash -g www www

# Copy existing application directory contents
COPY ./ucreativa-api/ /var/www/

# Copy existing application directory permissions
COPY --chown=www:www ./ucreativa-api/ /var/www

# Change current user to www
USER www

# Expose port 9000 and start php-fpm server

CMD ["php-fpm"]