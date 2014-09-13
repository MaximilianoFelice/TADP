require "TP1/Prototyped"
require "TP1/Singleton_Module_Behaviour"

module TP

  module PrototypedConstructor

    attr_accessor :constructor, :instance

    def method_missing
      p "hola"
    end

    def initialize(instance, block)
      @instance = instance
      @constructor = block
    end

    def new(*args)
      @constructor.yield(@instance, *args)
    end

  end

end