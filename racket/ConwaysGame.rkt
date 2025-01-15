#lang racket/gui

(require racket/class)

; Constants for the game
(define CELL-SIZE (if (positive? 20) 20 (error "CELL-SIZE must be positive")))
(define GRID-WIDTH (if (positive? 30) 30 (error "GRID-WIDTH must be positive")))
(define GRID-HEIGHT (if (positive? 30) 30 (error "GRID-HEIGHT must be positive")))
(define WINDOW-WIDTH (* CELL-SIZE GRID-WIDTH))
(define WINDOW-HEIGHT (* CELL-SIZE GRID-HEIGHT))

; Create a 2D vector to represent the grid
(define (make-grid)
  (build-vector GRID-HEIGHT
                (lambda (_)
                  (build-vector GRID-WIDTH
                                (lambda (_)
                                  (zero? (random 5)))))))

; Count live neighbors for a cell
(define (count-neighbors grid x y)
  (for/sum ([dx (in-range -1 2)]
            [dy (in-range -1 2)]
            #:when (not (and (zero? dx) (zero? dy))))
    (let ([nx (+ x dx)]
          [ny (+ y dy)])
      (if (and (>= nx 0) (< nx GRID-WIDTH)
               (>= ny 0) (< ny GRID-HEIGHT)
               (vector-ref (vector-ref grid ny) nx))
          1
          0))))

; Update the grid according to Conway's rules
(define (update-grid grid)
  (build-vector
   GRID-HEIGHT
   (lambda (y)
     (build-vector
      GRID-WIDTH
      (lambda (x)
        (let* ([alive? (vector-ref (vector-ref grid y) x)]
               [neighbors (count-neighbors grid x y)])
          (or (and alive? (or (= neighbors 2)
                             (= neighbors 3)))
              (and (not alive?) (= neighbors 3)))))))))

; Define the canvas class
(define game-canvas%
  (class canvas%
    (init-field grid)
    
    (define/override (on-paint)
      (let ([dc (send this get-dc)])
        (send dc clear)
        (for* ([y (in-range GRID-HEIGHT)]
               [x (in-range GRID-WIDTH)])
          (when (vector-ref (vector-ref grid y) x)
            (send dc set-brush "black" 'solid)
            (send dc set-pen "black" 1 'solid)
            (send dc draw-rectangle
                  (* x CELL-SIZE)
                  (* y CELL-SIZE)
                  CELL-SIZE
                  CELL-SIZE)))))
    
    (define/override (on-event event)
      (when (send event button-down?) ; Check if the mouse button is down
        (define x (quotient (send event get-x) CELL-SIZE))
        (define y (quotient (send event get-y) CELL-SIZE))
        (when (and (>= x 0) (< x GRID-WIDTH)
                   (>= y 0) (< y GRID-HEIGHT))
          (define cell (vector-ref (vector-ref grid y) x))
          (vector-set! (vector-ref grid y) x (not cell))
          (send this refresh))))
    
    (super-new)))

; Create the main frame
(define frame
  (new frame%
       [label "Conway's Game of Life"]
       [width WINDOW-WIDTH]
       [height WINDOW-HEIGHT]
       [alignment '(center center)]))

; Initialize the grid
(define current-grid (make-grid))

; Create the canvas
(define canvas
  (new game-canvas%
       [parent frame]
       [grid current-grid]
       [min-width WINDOW-WIDTH]
       [min-height WINDOW-HEIGHT]))

; Update function for animation
(define (update)
  (set! current-grid (update-grid current-grid))
  (send canvas refresh)
  (sleep/yield 0.1))

; Start the animation
(define (start-animation)
  (let loop ()
    (update)
    (loop)))

; Button to start/stop the animation
(define running? (box #f)) ; Use a box to hold the running state

(define start-stop-button
  (new button%
       [parent frame]
       [label "Start"]
       [callback (lambda (button event)
                    (set-box! running? (not (unbox running?)))
                    (send button set-label (if (unbox running?) "Stop" "Start"))
                    (if (unbox running?)
                        (thread start-animation)
                        (void)))]))

; Button to reset the grid
(define reset-button
  (new button%
       [parent frame]
       [label "Reset"]
       [callback (lambda (button event)
                    (set! current-grid (make-grid))
                    (send canvas refresh))]))

; Show the frame and start the game
(send frame show #t)
