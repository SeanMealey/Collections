#lang racket

;1.
(define (square x)
  (* x x))

(define (fourthPower2 x)
  (*  (square x) (square x)) 
)
(fourthPower2 3)

;2. 3x^2 + 4x + 1 for a numerical parameter to the function x

(define(func x)
  (+ (+ (* 3 (* x x)) (* 4 x)) 1)
)
(func 2)


;3. Power function implementation
(define (power x n)
  (cond
    [(= n 0) 1]                  ; base case: x^0 = 1
    [(= n 1) x]                  ; base case: x^1 = x
    [else (* x (power x (- n 1)))]))  ; recursive case: x^n = x * x^(n-1)

; Test the power function
(power 2 3)  ; Should return 8
(power 3 4)  ; Should return 81

;4. Logarithm function implementation
(define (logarithm x y)
  (cond
    [(= y x) 1]                    ; base case: if y equals x, return 1
    [(< y x) 0]                    ; error case: y should be >= x
    [else (+ 1 (logarithm x (/ y x)))]))  ; recursive case: log_x(y) = 1 + log_x(y/x)

; Test the logarithm function
(logarithm 2 8)   ; Should return 3 (2^3 = 8)
(logarithm 3 27)  ; Should return 3 (3^3 = 27)
